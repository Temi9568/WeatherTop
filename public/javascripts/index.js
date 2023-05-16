

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
const forms = document.querySelectorAll("form");

forms.forEach(form => {
    form.addEventListener('submit', (e) => {
        e.preventDefault();

        // String Validation
        let stringFields = form.querySelectorAll('input.string');
        stringFields.forEach(elem => {
            if (!elem.value.match(/^[A-Za-z\s]*$/)) {
                alert(`Please enter a valid ${elem.name.toUpperCase()}. It must contain only letters.`)
                return
            }
        })

        // Email Validation todo (can delete as bulma already have their own validator)
        let emailFields = form.querySelectorAll('input.email');
        emailFields.forEach(elem => {
            if (!elem.value.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/)) {
                alert(`Please enter a valid ${elem.name}.`)
                return
            }
        })

        // Password Validation
        let passwordFields = form.querySelectorAll('input.password');
        passwordFields.forEach(elem => {
            if (elem.value.length < 8 || !elem.value.match(/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/)) {
                alert(`Please enter a valid ${elem.name}. ${elem.name} must be at least 8 characters and contain one number and special character.`)
                return
            }
        })

        e.target.submit();

    })
})




