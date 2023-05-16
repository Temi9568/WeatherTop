

// Collapsible Button for HTML/CSS
const icons = document.querySelectorAll(".collapse-icon");
const tables = document.querySelectorAll(".readings-table");

for (let i=0;  i < icons.length; i++) {
    icons[i].addEventListener('click', () => {
        if (tables[i].classList.contains('collapsed')) {
            tables[i].style.display = "table";
            icons[i].innerHTML = '<i class="fa-regular fa-square-minus fa-2x"></i>';
        } else {
            tables[i].style.display = "none";
            icons[i].innerHTML = '<i class="fa-regular fa-square-plus fa-2x"></i>';
        }

        tables[i].classList.toggle('collapsed');
    });
}

// Form Validation
const forms = document.querySelectorAll("form.input-form");
forms.forEach(form => {
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        // Flag to track if there are any validation errors
        let hasErrors = false;

        // String Validation
        let stringFields = form.querySelectorAll('input.string');
        stringFields.forEach(elem => {
            if (!elem.value.match(/^[A-Za-z\s]*$/)) {
                alert(`Please enter a valid ${elem.name.toUpperCase()}. It must contain only letters.`);
                hasErrors = true;
            }
        });
        //
        // // Email Validation
        // let emailFields = form.querySelectorAll('input.email');
        // emailFields.forEach(elem => {
        //     if (!elem.value.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/)) {
        //         alert(`Please enter a valid ${elem.name}.`);
        //         hasErrors = true;
        //     }
        // });

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





