// --------------------------- Collapsible Button handlers  ---------------------------
function collapseStationPane(event, i) {
    // Find the readings table to collapse
    let stationPane;
    const stations = document.querySelectorAll(".station");
    for (let k = 0; k < stations.length; k++) {
        let stationId = stations[k].id;
        try {
            stationId = stationId.match(/\d+/)[0];
            if (stationId == i) {   // Relatice equality which is fine as stationId is an int while id a String
                stationPane = stations[k].querySelector('.pane');
                break;
            }
        } catch {

        }

    }

    // Collapse
    if (stationPane) {
        if (stationPane.classList.contains('collapsed')) {
            stationPane.style.display = 'flex';
            event.target.classList.replace('fa-square-plus', 'fa-square-minus');
        } else {
            stationPane.style.display = "none";
            event.target.classList.replace('fa-square-minus', 'fa-square-plus');
        }
        stationPane.classList.toggle('collapsed');
    }
}

function collapseReadingsTable(event, i) {
    // Find the station that has the pane we want to collapse
    let readingsTable;
    const stations = document.querySelectorAll(".station");
    for (let k = 0; k < stations.length; k++) {
        let stationId = document.location.href.split("/");
        stationId = stationId[stationId.length - 1];
        if (stationId == i) {
            readingsTable = stations[k].querySelector('.readings-table');
            break;
        }
    }

    // Collapse
    if (readingsTable) {
        if (readingsTable.classList.contains('collapsed')) {
            readingsTable.style.display = "table";
            event.target.classList.replace('fa-square-plus', 'fa-square-minus');
        } else {
            readingsTable.style.display = "none";
            event.target.classList.replace('fa-square-minus', 'fa-square-plus');
        }
        readingsTable.classList.toggle('collapsed');
    }
}


// --------------------------- Form Validation ---------------------------
const forms = document.querySelectorAll("form.input-form");
forms.forEach(form => {
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        // Tracks if there are any validation errors
        let hasErrors = false;

        // String Validation
        let stringFields = form.querySelectorAll('input[type="text"]');
        stringFields.forEach(elem => {
            if (!elem.value.match(/^[A-Za-z\s]*$/)) {
                alert(`Please enter a valid ${elem.name.toUpperCase()}. It must contain only letters.`);
                hasErrors = true;
            }
        });

        // Password Validation
        let passwordFields = form.querySelectorAll('input.password');
        passwordFields.forEach(elem => {
            if (elem.value.length < 8 || !elem.value.match(/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/)) {
                alert(`Please enter a valid ${elem.name}. ${elem.name} must be at least 8 characters and contain one number and special character.`);
                hasErrors = true;
            }
        });

        if (!hasErrors) {
            e.target.submit();
        }
    });
});

// --------------------------- Handles delete POST reqs (just ensures dialog box pops up) ---------------------------
const deleteForms = document.querySelectorAll('form.delete-form');
deleteForms.forEach(form => {
    form.addEventListener('submit', e => {
        e.preventDefault();

        if (confirm("Are you sure you want to delete this?")) {
            e.target.submit();
        } else {
            return false;
        }
    })
})


// On the relevant station page, when users want to add new reading, the below ensures the date shown is NOW
const displayCurrentDate = () => {
    let now = new Date();
    const options = {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
    };
    const formattedDate = now.toISOString().slice(0, 19).replace("T", " ");

    let elems = document.querySelectorAll('input[name="date"]')
    elems.forEach(elem => {
        elem.placeholder = formattedDate;
    })
}

setInterval(displayCurrentDate, 1000);





