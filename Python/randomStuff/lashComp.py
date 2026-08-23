import re

MOVE_RE = re.compile(r'^(G0|G00|G1|G01)\s', re.IGNORECASE)

X_RE = re.compile(r'X([-+]?\d*\.?\d+)')
Y_RE = re.compile(r'Y([-+]?\d*\.?\d+)')


class BacklashCompensator:
    def __init__(self, backlash_x, backlash_y, debug = False):
        self.debug = debug
        self.backlash_x = backlash_x
        self.backlash_y = backlash_y

        self.virtual_x = 0
        self.virtual_y = 0

        self.offset_x = 0.0
        self.offset_y = 0.0

        self.last_dir_x = 0
        self.last_dir_y = 0

    @staticmethod
    def sign(v):
        if v > 0:
            return 1
        if v < 0:
            return -1
        return 0

    def process_move(self, line):
        x_match = X_RE.search(line)
        y_match = Y_RE.search(line)

        if self.virtual_x is None:
            if x_match:
                self.virtual_x = float(x_match.group(1))
            else:
                self.virtual_x = 0.0

        if self.virtual_y is None:
            if y_match:
                self.virtual_y = float(y_match.group(1))
            else:
                self.virtual_y = 0.0

        new_x = self.virtual_x
        new_y = self.virtual_y

        if x_match:
            new_x = float(x_match.group(1))

        if y_match:
            new_y = float(y_match.group(1))

        dx = new_x - self.virtual_x
        dy = new_y - self.virtual_y

        dir_x = self.sign(dx)
        dir_y = self.sign(dy)

        takeup_x = 0
        takeup_y = 0

        if dir_x and dir_x != self.last_dir_x:
            takeup_x = dir_x * self.backlash_x
            self.offset_x += takeup_x

        if dir_y and dir_y != self.last_dir_y:
            takeup_y = dir_y * self.backlash_y
            self.offset_y += takeup_y

        compensated_x = new_x + self.offset_x
        compensated_y = new_y + self.offset_y

        newline = line.rstrip()

        takeup_line = ''
            
        if x_match or y_match:
            if x_match:
                takeup_line += f'X{self.virtual_x + self.offset_x} '

            if y_match:
                takeup_line += f'Y{self.virtual_y + self.offset_y} '

            takeup_line = 'G1 '+ takeup_line + '\n' 

        if x_match:
            newline = X_RE.sub(f'X{compensated_x:.4f}', newline)

        if y_match:
            newline = Y_RE.sub(f'Y{compensated_y:.4f}', newline)


        if self.debug:
            original_coords = []

            if x_match:
                original_coords.append(f'X{new_x:.4f}')

            if y_match:
                original_coords.append(f'Y{new_y:.4f}')

            if original_coords:
                newline += f' (ORIG {" ".join(original_coords)})'

        newline += "\n"

        self.virtual_x = new_x
        self.virtual_y = new_y

        if dir_x:
            self.last_dir_x = dir_x

        if dir_y:
            self.last_dir_y = dir_y

        return takeup_line + newline


def process_file(input_file, output_file, backlash_x, backlash_y, debug = False):
    comp = BacklashCompensator(backlash_x, backlash_y, debug)

    with open(input_file, "r") as fin, open(output_file, "w") as fout:
        for line in fin:
            if MOVE_RE.match(line):
                line = comp.process_move(line)
            fout.write(line)


if __name__ == "__main__":
    process_file(
        "pre.gcode",
        "psot.gcode",
        backlash_x=0.200,
        backlash_y=1.000,
        debug = False
    )