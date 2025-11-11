//=================BOOTSTRAP TITULO=======================
var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
	return new bootstrap.Tooltip(tooltipTriggerEl)
})
//=================ALERTA TEMPORAL=======================
// $("#noexistente").delay(4000).slideUp(200, function() {
//     $(this).alert('close');
// });