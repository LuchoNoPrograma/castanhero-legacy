//===================SCRIPT ANADIR INPUT DINAMICO====================
let a = $('#selectProgramas')[0]


let template = `<div class="input-group my-4"> \
                    ${a.outerHTML} \
                </div>`

let minusButton = '<span class="btn btn-danger input-group-addon delete-field d-flex justify-content-center align-items-center"> \
                        <i class="fa-solid fa-minus"></i> \
                    </span>';
let limiteInput = 5;
let i = 0

$('.add-field').click(function () {
    if (i < limiteInput) {
        let temp = $(template).insertBefore('.help-block');
        temp.append(minusButton);
        i++;
    }
});

$('.fields').on('click', '.delete-field', function () {
    $(this).parent().remove();
    i--;
});

var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
		var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
			return new bootstrap.Tooltip(tooltipTriggerEl)
})