function selecionarData(data) {
    const inputData = document.querySelector('#form-info-tutoria input#data');
    const inputSubmit = document.querySelector('#form-info-tutoria input[type=submit]');

    inputData.value = data;
    inputSubmit.click();
}