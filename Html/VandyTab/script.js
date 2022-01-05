var preCompiledRegex = /<li>Bus(.*?)<\/li>/g;
var menuLevel = 0;
var dayArray = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
var myName = localStorage.name;

var header, jumbotron, supporting, data;
header = document.querySelector(".header");
jumbotron = document.querySelector(".jumbotron");
supporting = document.querySelector(".supporting");
data = document.querySelector(".data");

var vanB, van1, van2, van3, van4, van5, van6, van7, van8, van9, van10, van11, van12, van13, running1, running2, running3;
vanB = document.getElementById('vanB');
van1 = document.getElementById('van1');
van2 = document.getElementById('van2');
van3 = document.getElementById('van3');
van4 = document.getElementById('van4');
van5 = document.getElementById('van5');
van6 = document.getElementById('van6');
van7 = document.getElementById('van7');
van8 = document.getElementById('van8');
van9 = document.getElementById('van9');
van10 = document.getElementById('van10');
van11 = document.getElementById('van11');
van12 = document.getElementById('van12');
van13 = document.getElementById('van13');
running1 = document.getElementById('running1');
running2 = document.getElementById('running2');
running3 = document.getElementById('running3');

var randdininghall =
    ["1",
        ["Monday", ["7", "11", "17"], ["30", "0", "0"],
            ["10", "15", "20"], ["0", "0", "0"]],
        ["Tuesday", ["7", "11", "17"], ["30", "0", "0"],
            ["10", "15", "20"], ["0", "0", "0"]],
        ["Wednesday", ["7", "11", "17"], ["30", "0", "0"],
            ["10", "15", "20"], ["0", "0", "0"]],
        ["Thursday", ["7", "11", "17"], ["30", "0", "0"],
            ["10", "15", "20"], ["0", "0", "0"]],
        ["Friday", ["7", "11"], ["30", "0"],
            ["10", "15"], ["0", "0"]],
        ["Saturday", ["10", "17"], ["0", "0"],
            ["14", "20"], ["0", "0"]],
        ["Sunday", ["10", "17"], ["0", "0"],
            ["14", "20"], ["0", "0"]]];

var theCommons =
    ["3",
        ["Monday", ["7", "11", "17"], ["30", "0", "0"],
            ["10", "15", "20"], ["0", "0", "0"]],
        ["Tuesday", ["7", "11", "17"], ["30", "0", "0"],
            ["10", "15", "20"], ["0", "0", "0"]],
        ["Wednesday", ["7", "11", "17"], ["30", "0", "0"],
            ["10", "15", "20"], ["0", "0", "0"]],
        ["Thursday", ["7", "11", "17"], ["30", "0", "0"],
            ["10", "15", "20"], ["0", "0", "0"]],
        ["Friday", ["7", "11"], ["30", "0"],
            ["10", "15"], ["0", "0"]],
        ["Saturday", ["10", "17"], ["0", "0"],
            ["14", "20"], ["0", "0"]],
        ["Sunday", ["10", "17"], ["0", "0"],
            ["14", "20"], ["0", "0"]]];

var placeArray = [randdininghall, theCommons];

function changeIt() {
    "use strict";
    //specify random images below. You can have as many as you wish
    var totalCount, num;
    totalCount = 6;

    num = Math.ceil(Math.random() * totalCount);
    document.body.background = 'img/back' + num + '.jpg';
    document.body.style.backgroundRepeat = "no-repeat";
    document.body.style.backgroundSize = "cover";// Background repeat
}

function setTimeAndGreeting() {
    "use strict";
    var d, hours, minutes, seconds, ap;

    d = new Date();
    ap = " AM";

    hours = d.getHours();
    minutes = d.getMinutes();
    seconds = d.getSeconds();

    if (hours > 4 && hours <= 11) {
        document.getElementById('clockbox').innerHTML = "Good morning, " + myName + ".";
    }
    else if (hours <= 18) {
        document.getElementById('clockbox').innerHTML = "Good afternoon, " + myName + ".";
    }
    else {
        document.getElementById('clockbox').innerHTML = "Good evening, " + myName + ".";
    }

    if (hours >= 12) {
        ap = " PM";
    }

    if (hours == 0) {
        hours += 12;
    }
    //hours = hours % 13;/************************/

    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }

    document.getElementById('clockbox1').innerHTML = hours + ":" + minutes + ":" + seconds;
}

function displayTime(Closed, hours, minutes, id) {
    "use strict";
    var image, ap;
    ap = "AM"


    if (minutes < 10) {
        minutes = "0" + minutes;
    }

    if (hours >= 12) {
        ap = "PM"
    }

    if (hours === 0) {
        hours = 12;
    }
    else if (hours > 12) {
        hours -= 12;
    }

    image = document.getElementById("open" + id);

    if (Closed) {
        image.src = "img/closed.png";
        document.getElementById(id).textContent = "Opens @ " + hours + ":" + minutes + " " + ap;
    }
    else {
        image.src = "img/open.png";
        document.getElementById(id).textContent = "Closes @ " + hours + ":" + minutes + " " + ap;
    }
}

function displayDay(day, id) {
    "use strict";
    var image;
    image = document.getElementById("open" + id);
    image.src = "img/closed.png";
    document.getElementById(id).textContent = "Opens on " + day;
}

function setDining(place) {
    "use strict";
    var d, hours, minutes, day;
    d = new Date();
    hours = d.getHours();
    minutes = d.getMinutes();
    day = d.getDay();

    outer:
    for (let i = 1; i < place.length; i++) {
        if (place[i][0] === dayArray[day]) {
            for (let j = 0; j < place[i][1].length; j++) {
                if (hours >= parseInt(place[i][1][j]) && minutes >= parseInt(place[i][2][j])) {
                    if ((hours < parseInt(place[i][3][j])) ||
                        (hours == parseInt(place[i][3][j]) && hours == parseInt(place[i][3][j]))) {
                        displayTime(false, place[i][3][j], place[i][4][j], place[0]);
                        break outer;
                    }
                }
            }

            if (i + 1 == place.length) {
                i = (i + 2) % place.length;
            }

            displayTime(true, place[i][1][0], place[i][2][0], place[0]);
            break;
        }
    }
}

function fadeOut(element) {
    "use strict";
    var timer, op;
    op = 1;  // initial opacity
    timer = setInterval(function () {
        if (op <= 0.1) {
            clearInterval(timer);
            element.style.display = 'none';
        }
        element.style.opacity = op;
        element.style.filter = 'alpha(opacity=' + op * 100 + ")";
        op -= op * 0.1;
    }, 50);
}

function fadeIn(element) {
    "use strict";
    var timer, op;
    op = 0.1;  // initial opacity
    timer = setInterval(function () {
        if (op >= 1) {
            clearInterval(timer);
        }
        element.style.opacity = op;
        element.style.filter = 'alpha(opacity=' + op * 100 + ")";
        op += op * 0.1;
    }, 10);
}

function vanDisplayNone(array) {
    array.forEach(element => {
        element.style.display = "none";
    });
}

function vanDisplayInline(array) {
    array.forEach(element => {
        element.style.display = "inline";
    });
}

function vanDisplayInlineBlock(array) {
    array.forEach(element => {
        element.style.display = "inline-block";
    });
}

function vandyTimes(link) {
    "use strict";
    var d, nhour;

    d = new Date();
    nhour = d.getHours();
    van2.setAttribute("style", "height:127px");
    vanDisplayNone([van3, van4, van5, van6, van7, van8, van9, van10, van11, van12, van13]);

    if (nhour >= 18 || nhour == 0) {
        $.ajax({
            url: link,
            success: function (data) {
                var matches = data.match(preCompiledRegex);  // regex parse to find relevant tag
                var message = '';
                if (matches === null) {
                    vanB.innerHTML = "No arrival times are available.";
                }
                else {
                    for (var i = 0; i < matches.length; i++) {
                        matches[i] = matches[i].substring(4, matches[i].length - 5);
                        message += matches[i] + "<br/>";
                    }
                    running1.setAttribute("style", "width:0px");
                    vanB.setAttribute("style", "width:300px");
                    vanB.innerHTML = message;
                }
            },
            error: function (data) {
                vanB.textContent = "No arrival times available.";
            }
        });
    }
    else {
        vanB.textContent = "No arrival times available.";
    }
}

function runningText() {
    d = new Date();
    hours = d.getHours();

    if (hours >= 18 || hours == 0) {
        running1.textContent = "Status: Running";
        running2.textContent = "Status: Running";
        running3.textContent = "Status: Running";
    }
    else {
        running1.textContent = "Status: Not Running";
        running2.textContent = "Status: Not Running";
        running3.textContent = "Status: Not Running";
    }
}

function clearRunningText(blackImage, goldImage, redImage) {
    blackImage.src = "";
    goldImage.src = "";
    redImage.src = "";

    running1.textContent = "";
    running2.textContent = "";
    running3.textContent = "";
}

function opacity0array(array) {
    array.forEach(element => {
        element.style.opacity = 0;;
    });
}

document.getElementById("nameSubmit").addEventListener("keydown", function (e) {
    "use strict";
    if (!e) {
        e = window.event;
    }

    if (e.keyCode === 13) {
        myName = document.getElementById("nameSubmit").value;
        fadeOut(header);
        localStorage.name = myName;
        setTimeAndGreeting();
        setInterval(setTimeAndGreeting, 1000);

        placeArray.forEach(element => {
            setDining(element);
        });

        header.style.display = "none";
        fadeIn(jumbotron);
        fadeIn(supporting);
        fadeIn(data);
    }
}, false);

document.addEventListener('DOMContentLoaded', function () {
    "use strict";
    var nameChange;
    nameChange = document.getElementById('clockbox');

    nameChange.addEventListener('click', function () {
        opacity0array([jumbotron, supporting, data, header]);
        header.style.display = "block";
        header.style.marginTop = "3.5em";
        fadeIn(header);
    });
});

document.addEventListener('DOMContentLoaded', function () {
    "use strict";
    var black, gold, red, redImage, goldImage, blackImage;
    black = false;
    gold = false;
    red = false;

    //onload
    if (menuLevel === 0) {
        runningText();
        vanDisplayNone([van1, van5, van6, van7, van8, van9, van10, van11, van12, van13]);
        van2.style.height = "54px";
        van3.style.height = "54px";
        van4.style.height = "54px";
        blackImage = document.getElementById("main");
        blackImage.src = "img/blackvan.png";
        goldImage = document.getElementById("perimeter");
        goldImage.src = "img/goldvan.png";
        redImage = document.getElementById("reverse");
        redImage.src = "img/redvan.png";
    }

    //back to main menu
    van1.addEventListener('click', function () {
        runningText();
        vanDisplayNone([van1, van5, van6, van7, van8, van9, van10, van11, van12, van13]);

        black = false;
        gold = false;
        red = false;

        goldImage.src = "img/goldvan.png";
        redImage.src = "img/redvan.png";
        blackImage.src = "img/blackvan.png";

        van2.setAttribute("style", "height:54px");
        van3.setAttribute("style", "height:54px");
        van4.setAttribute("style", "height:54px");

        vanB.textContent = "Main Route (Black)";
        document.getElementById('vanC').textContent = "Perimeter Route (Gold)";
        document.getElementById('vanD').textContent = "Reverse Route (Red)";

        running1.setAttribute("style", "width:170px");

        menuLevel = 0;
    });

    //main route
    van2.addEventListener('click', function () {
        if (menuLevel === 0) {
            document.getElementById('vanA').textContent = "< Back";
            vanDisplayInline([van2, van3, van4]);
            vanDisplayInlineBlock([van1, van5, van6, van7, van8, van9]);

            vanB.textContent = "Branscomb Quad";
            document.getElementById('vanC').textContent = "Carmichael Towers";
            document.getElementById('vanD').textContent = "Kissam-Kirkland";
            document.getElementById('vanE').textContent = "21st near Terrace Place";
            document.getElementById('vanF').textContent = "Wesley Place";
            document.getElementById('vanG').textContent = "1801 Edgehill";
            document.getElementById('vanH').textContent = "Hank Ingram";
            document.getElementById('vanI').textContent = "Highland Quad";

            clearRunningText(blackImage, goldImage, redImage);

            menuLevel = 1;
            black = true;
        }
        else if (menuLevel === 1 && black) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1290/direction/1857/stops/263473/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/263473/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && red) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1291/direction/1858/stops/263473/pattern");
            menuLevel = 2;
        }
    });

    //perimeter route
    van3.addEventListener('click', function () {
        if (menuLevel === 0) {
            document.getElementById('vanA').textContent = "< Back";

            vanDisplayInline([van2, van3, van4]);

            vanDisplayInlineBlock([van1, van5, van6, van7, van8, van9, van10, van11, van12, van13]);

            vanB.textContent = "Branscomb Quad";
            document.getElementById('vanC').textContent = "Carmichael Towers";
            document.getElementById('vanD').textContent = "Kissam-Kirkland";
            document.getElementById('vanE').textContent = "21st near Terrace Place";
            document.getElementById('vanF').textContent = "MRB3";
            document.getElementById('vanG').textContent = "North Hall";
            document.getElementById('vanH').textContent = "Blair";
            document.getElementById('vanI').textContent = "Highland Quad";
            document.getElementById('vanJ').textContent = "McGugin";
            document.getElementById('vanK').textContent = "V.U.P.D.";
            document.getElementById('vanL').textContent = "Barnes & Noble";
            document.getElementById('vanM').textContent = "Lupton";

            clearRunningText(blackImage, goldImage, redImage);

            menuLevel = 1;
            gold = true;
        }
        else if (menuLevel === 1 && black) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1290/direction/1857/stops/263470/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/263470/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && red) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1291/direction/1858/stops/264041/pattern");
            menuLevel = 2;
        }
    });

    //reverse route
    van4.addEventListener('click', function () {
        if (menuLevel === 0) {
            document.getElementById('vanA').textContent = "< Back";

            vanDisplayInline([van2, van3, van4]);

            vanDisplayInlineBlock([van1, van5, van6, van7]);

            vanB.textContent = "Branscomb Quad";
            document.getElementById('vanC').textContent = "V.U.P.D.";
            document.getElementById('vanD').textContent = "Highland Quad";
            document.getElementById('vanE').textContent = "Blair";
            document.getElementById('vanF').textContent = "Hank Ingram";
            document.getElementById('vanG').textContent = "Carmichael Towers";

            clearRunningText(blackImage, goldImage, redImage);

            menuLevel = 1;
            red = true;
        }
        else if (menuLevel === 1 && black) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1290/direction/1857/stops/1198824/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/1198824/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && red) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1291/direction/1858/stops/263444/pattern");
            menuLevel = 2;
        }
    });

    van5.addEventListener('click', function () {
        if (menuLevel === 1 && black) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1290/direction/1857/stops/644873/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/644873/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && red) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1291/direction/1858/stops/1547179/pattern");
            menuLevel = 2;
        }
    });

    van6.addEventListener('click', function () {
        if (menuLevel === 1 && black) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1290/direction/1857/stops/1198825/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/1547177/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && red) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1291/direction/1858/stops/644903/pattern");
            menuLevel = 2;
        }
    });

    van7.addEventListener('click', function () {
        if (menuLevel === 1 && black) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1290/direction/1857/stops/1570180/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/1547178/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && red) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1291/direction/1858/stops/263470/pattern");
            menuLevel = 2;
        }
    });

    van8.addEventListener('click', function () {
        if (menuLevel === 1 && black) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1290/direction/1857/stops/644903/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/1547179/pattern");
            menuLevel = 2;
        }
    });

    van9.addEventListener('click', function () {
        if (menuLevel === 1 && black) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1290/direction/1857/stops/263444/pattern");
            menuLevel = 2;
        }
        else if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/263444/pattern");
            menuLevel = 2;
        }
    });

    van10.addEventListener('click', function () {
        if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/1547180/pattern");
            menuLevel = 2;
        }
    });

    van11.addEventListener('click', function () {
        if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/264041/pattern");
            menuLevel = 2;
        }
    });

    van12.addEventListener('click', function () {
        if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/1547181/pattern");
            menuLevel = 2;
        }
    });

    van13.addEventListener('click', function () {
        if (menuLevel === 1 && gold) {
            vandyTimes("http://cors-anywhere.herokuapp.com/www.vandyvans.com/simple/routes/1289/direction/3021/stops/238066/pattern");
            menuLevel = 2;
        }
    });
});

jQuery(function ($) {
    $(document.body).fadeIn(1000);
});

window.onload = function () {
    "use strict";
    changeIt();
    opacity0array([jumbotron, supporting, data]);

    if (myName === undefined) {
        header.style.opacity = 0;
        fadeIn(header);
    }
    else {
        header.style.marginTop = "0px";
        header.style.display = "none";
        fadeIn(jumbotron);
        fadeIn(supporting);
        fadeIn(data);
        setTimeAndGreeting();
        setInterval(setTimeAndGreeting, 1000);
        placeArray.forEach(element => {
            setDining(element);
        });
    }
};