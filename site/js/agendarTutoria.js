const btnAgendar = document.querySelector('input#btn-agendar');

if (btnAgendar != null) {
    btnAgendar.addEventListener('mousedown', (event) => {
        event.preventDefault();

        const formsSelecionar = document.querySelectorAll('form.form-selecionar-horario');
        let idHorario = null;

        for (let form of formsSelecionar) {
            const children = form.childNodes;
            const inputSelecionar = children[3];

            if (inputSelecionar === document.activeElement) {
                idHorario = children[1].value;
            }
        }

        if (idHorario !== null) {
            agendarTutoria(idHorario);
        } else {
            alert('Nenhum horário selecionado!');
        }
    });
}

function agendarTutoria(idhorario) {
    const inputHorario = document.querySelector('form#form-envio-tutoria input[name="idhorario"]');
    const inputSubmit = document.querySelector('form#form-envio-tutoria input[type="submit"]');

    inputHorario.value = idhorario;

    inputSubmit.click();
}
