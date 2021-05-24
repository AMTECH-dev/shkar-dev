log("Connecting listeners...");

let line;
let chartDIV;
let resizer;
let historyDIV;

let isLocked = true;
let chart_width = 0;
let change = 0;

let iconsPlaces;


$(document).ready(function() {
    line = $('.milesLine');
    chartDIV = $('.milestoneDiv');
    resizer = $('#resizeSpan');
    historyDIV = $('.history');

    chart_width = chartDIV.width();

    iconsPlaces = document.getElementsByName("dataLineIcon");
    iconsPlaces.forEach(function setIcon(elem) {
        elem.src = './res/media/nameIcon.png';
    });


    $(document).mousemove(function (e) {
        debug("chart_width:" + chart_width + "; line.width(): " + line.width() + "; mouse X: " + e.clientX + "; locked = " + isLocked);

        if (!isLocked) {
            disposeChart();
            change = e.clientX;

            if (chart_width < line.width() && chart_width > 0) {
                chartDIV.css("width", change);
                historyDIV.css("margin-left", change + 1);
            } else {debug("chart_width:" + chart_width + "; line.width(): " + line.width());}
        }
    });

    $(document).mousedown(function (e) {
        if (e.target.type === 'checkbox') {
            switch (e.target.id) {
                case "filterOption01":
                    if (TSort) {
                        TSort = false;
                    } else {
                        TSort = true;
                    }
                    // chart.destroy();
                    reloadChart();
                    break;

                case "filterOption02":
                    console.log("CheckBox: '" + elem02.className + "' is selected: " + elem02.checked);
                    break;

                case "filterOption03":
                    console.log("CheckBox: '" + elem03.className + "' is selected: " + elem03.checked);
                    break;

                case "filterOption04":
                    console.log("CheckBox: '" + elem04.className + "' is selected: " + elem04.checked);
                    break;

                default:
            }
        } else if (e.target.type === 'textarea') {
            let name = prompt('Enter a text');
            e.target.textContent = 'Message: ' + name;
        }
    });

    $(resizer).mousedown(unlockMove);
    $(document).mouseup(lockMove);
    $(resizer).mouseup(lockMove);
});


log("Listeners are connected!");

function lockMove() {
    if (isLocked) {return;}

    isLocked = true;

    try {
        resizer.css("background-color", "#f93");
        chart_width = chartDIV.width();

        loadChart();

        log("changed lock ON (" + isLocked + ")");
    } catch (ex) {log(ex);}
}

function unlockMove() {
    isLocked = false;
    chartDIV.css("width", line.clientX);
    historyDIV.css("margin-left", line.clientX + 1);

    try {
        chart_width = chartDIV.width();
        resizer.css("background-color", "#00f");

        log("changed lock OFF (" + isLocked + ")");
    } catch (ex) {log(ex);}
}