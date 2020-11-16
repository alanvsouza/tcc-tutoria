const inputHorariosDinamicos = document.querySelector('input#input-atualizar-horarios-dinamicos');
const horariosDinamicos = document.querySelectorAll('#dia-semana > input');
const buttonSubmitHorariosDinamicos = document.querySelector('#btn-atualizar-horarios-dinamicos');

buttonSubmitHorariosDinamicos.addEventListener('click', (event) => {
    let stringHorarios = '';
    for (let horario of horariosDinamicos) {

        if (horario.value != '') {
            stringHorarios += horario.value + ';';
        }

        inputHorariosDinamicos.value = stringHorarios;
    }

    document.querySelector('body > div:nth-child(3) > form.atualizar-horarios').submit();
});
