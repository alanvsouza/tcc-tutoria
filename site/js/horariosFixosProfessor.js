const inputsHorariosFixos = document.querySelectorAll('#form-horarios-fixos > input.input-horarios-fixos');
const horariosFixos = document.querySelectorAll('ul#ul-horarios-fixos > li > input.input-hora');
const buttonSubmitHorariosFixos = document.querySelector('#btn-horarios-fixos');

buttonSubmitHorariosFixos.addEventListener('click', (event) => {
    let horarios = [];

    console.log(horariosFixos);

    let numHorario = 0;
    let stringHorarios = '';
    for (let horario of horariosFixos) {
        if (horario.value != '') {
            stringHorarios += horario.value + ';';
        }

        if (numHorario === 3) {
            numHorario = 0;

            if (stringHorarios != '') {
                horarios.push(stringHorarios);

                stringHorarios = '';
            }

            continue;
        }

        numHorario++;
    }

    console.log(horarios);

    for (let [index, horario] of horarios.entries()) {
        let inputHorario = document.querySelector(`input#dia-${index + 1}`);

        inputHorario.value += horario;
    }

    const inputSubmit = document.querySelector('#btn-submit-horarios-fixos');
    inputSubmit.click();
});
