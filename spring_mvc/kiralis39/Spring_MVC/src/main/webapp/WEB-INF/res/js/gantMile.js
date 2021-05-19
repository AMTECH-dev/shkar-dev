console.log("Loading gantMile.js");

google.charts.load('current', {'packages':['gantt']});
google.charts.setOnLoadCallback(reloadChart);

let chart;
let data;
let options;
let TSort = true;

let rows = 6;
let rowHeight = 36;
let vertShift = 3;


function daysToMilliseconds(days) {
    return days * 24 * 60 * 60 * 1000;
}

function buildData() {
    data = new google.visualization.DataTable();
    data.addColumn('string', 'Task ID');
    data.addColumn('string', 'Task Name');
    data.addColumn('string', 'Resource');
    data.addColumn('date', 'Start Date');
    data.addColumn('date', 'End Date');
    data.addColumn('number', 'Duration');
    data.addColumn('number', 'Percent Complete');
    data.addColumn('string', 'Dependencies');

    data.addRows([
        ['Research', 'Find sources', '0',
            new Date(2015, 0, 1), new Date(2015, 0, 5),
            null,  100,  null],

        ['Write', 'Write paper', '1',
            null, new Date(2015, 0, 9),
            daysToMilliseconds(3), 25, 'Research,Outline'],

        ['Cite', 'Create bibliography', '1',
            null, new Date(2015, 0, 7),
            daysToMilliseconds(1), 20, 'Research'],

        ['Complete', 'Hand in paper', '0',
            null, new Date(2015, 0, 10),
            daysToMilliseconds(1), 0, 'Cite,Write'],

        ['Outline', 'Outline paper', '0',
            null, new Date(2015, 0, 6),
            daysToMilliseconds(1), 50, 'Research'],

        ['Temporary', 'Test chunk', '2',
            new Date(2015, 0, 3), null,
            daysToMilliseconds(3), 75, null]
    ]);
}

function buildOptions() {
    options = {
        title: 'London Olympics Medals',

        legend: {
            position: 'none'
        },

        backgroundColor: {
            fill: '#404044'
        },

        gantt: {
            defaultStartDateMillis: new Date(2021, 5, 1),

            criticalPathEnabled: true,
            criticalPathStyle: {
                stroke: '#e64a19',
                strokeWidth: 4
            },
            arrow: {
                angle: 30,
                width: 2,
                color: 'lime',
                radius: 30,
                length: 8,
                spaceAfter: 8
            },
            innerGridHorizLine: {
                stroke: '#303032FF',
                strokeWidth: vertShift
            },
            labelStyle: {
                fontName: 'Roboto2',
                fontSize: 16,
                color: '#757575'
            },
            innerGridTrack: {fill: '#445'},
            innerGridDarkTrack: {fill: '#446'},
            barCornerRadius: 9,
            trackHeight: rowHeight,
            barHeight: rowHeight * 0.85,
            percentEnabled: true,
            shadowEnabled: true,
            shadowOffset: 2,
            sortTasks: TSort
            // labelMaxWidth: 128
        }
    };
}

function disposeChart() {
    $(".milestoneDiv").replaceWith($('<div class="milestoneDiv"></div>'));
    chartDIV = $(".milestoneDiv");
}

function reloadChart() {
    console.log("Recreating chart...");

    let msDiv = document.getElementsByClassName('milestoneDiv')[0];
    let msHeight = msDiv.clientHeight;
    // let msHeight = msDiv.offsetHeight;
    rowHeight = msHeight / rows;

    buildData();
    buildOptions();

    chart = new google.visualization.Gantt(msDiv);
    chart.draw(data, options);
}