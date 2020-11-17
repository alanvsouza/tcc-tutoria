const buttonConsultarData = document.querySelector('button#btn-consultar-horarios');

buttonConsultarData.addEventListener('click', (event) => {
    console.log('entrei');

    const inputData = document.getElementById('input-horarios-dinamico');
    const inputDataHidden = document.getElementById('input-horarios-dinamico-hidden');

    inputDataHidden.value = inputData.value;

    document.getElementById('form-consultar-data').submit();
});