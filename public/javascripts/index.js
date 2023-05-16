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