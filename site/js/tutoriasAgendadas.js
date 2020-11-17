const formsTutoria = document.querySelectorAll('.form-btn-tutoria');

for (let form of formsTutoria) {
    form.addEventListener('submit', (event) => {
        if (form.querySelector('input[name="local"]').value == "") {
            event.preventDefault();
            alert('O campo de local está vazio!');
        }
    })
}