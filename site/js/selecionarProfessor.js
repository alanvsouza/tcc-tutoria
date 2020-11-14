let combobox = document.querySelector('select#selecionar-tutor');
const submitComboboxButton = document.querySelector('input#select-professor-combobox');

combobox.addEventListener('change', (event) => {
    submitComboboxButton.click();
});
