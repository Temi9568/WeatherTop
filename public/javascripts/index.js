

// Collapsible Button for HTML/CSS
const icons = document.querySelectorAll(".collapse-icon");
const tables = document.querySelectorAll(".readings-table");

function collapseStationPane(event, i) {
    // Find the readings table to collapse
    let stationPane;
    const stations = document.querySelectorAll(".station");
    for (let k=0; k<stations.length; k++) {
        let stationId = stations[k].id;
        try {
            stationId = stationId.match(/\d+/)[0];
            if (stationId == i) {
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
    // Find the readings table to collapse
    let readingsTable;
    const stations = document.querySelectorAll(".station");
    for (let k=0; k<stations.length; k++) {
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




// Form Validation
const forms = document.querySelectorAll("form.input-form");
forms.forEach(form => {
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        // Flag to track if there are any validation errors
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
        let passwordFields = form.querySelectorAll('input[type="password"]');
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


const deleteForms = document.querySelectorAll('form.delete-form');
deleteForms.forEach(form => {
    form.addEventListener('submit', e => {
        e.preventDefault();

        if (confirm("Are you sure you want to delete this?")) {
            console.log("dsjdsjhdshjsd");
            e.target.submit();
        } else {
            return false;
        }
    })
})

// const formButtons =  document.querySelector("#edit-member-details").querySelectorAll("button[type='button']");
// const formInputs =  document.querySelector("#edit-member-details").querySelectorAll("input");
// const submitBtn = document.querySelector("#edit-member-details").querySelector("button[type='submit']");
//
//
// formButtons.forEach((btn, i) => {
//     btn.addEventListener("click", e => {
//         if(e.target.classList.contains("unedited")) {
//             e.target.innerText = "Save";
//             e.target.classList.replace("is-dark", "is-success");
//             e.target.classList.remove("unedited");
//             formInputs[i].disabled = false;
//         } else {
//             let valid = false;
//             if (i === 3 && formInputs[i].value !== undefined) {  // Password Field
//                 if (formInputs[i].value.length < 8 || !formInputs[i].value.match(/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/)) {
//                     alert(`Please enter a valid password. Password must be at least 8 characters and contain one number and special character.`);
//                     formInputs[i].value = orignalPassword;
//                     submitBtn.disabled = true;
//                     return
//                 }
//             }
//             e.target.classList.replace("is-success", "is-dark");
//             formInputs[i].disabled = true;
//             e.target.classList.add("unedited");
//             e.target.innerText = "Edit Details!";
//             submitBtn.disabled = false;
//             // document.querySelector("#edit-member-details").submit();
//         }
//     })
// })


document.getElementById("edit-member-details").addEventListener('submit', e => {
    e.preventDefault();

    if (confirm("Are you sure you want to save these changes?")) {
        formInputs.forEach(elem => {elem.disabled = false;})
        e.target.submit();
    }
})

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





