function verificar() {
    const valor = document.getElementById('numero').value;
    const numero = Number(valor);
    const alerta = document.getElementById('alerta');

    alerta.innerHTML = ""; // Limpiamos alertas previas

    let clase = '';
    let mensaje = '';

    if (!valor) {
        clase = 'alert-warning';
        mensaje = 'Por favor, ingresa un número.';
    } else if (!Number.isInteger(numero)) {
        clase = 'alert-danger';
        mensaje = 'Solo se permiten números enteros.';
    } else {
        clase = 'alert-success';
        mensaje = `Número válido: ${numero}`;
    }

    // Creamos el div flotante
    const div = document.createElement('div');
    div.className = `alert ${clase}`;
    div.textContent = mensaje;
    div.style.minWidth = '300px';
    div.style.textAlign = 'center';

    alerta.appendChild(div);

    // Desaparece después de 3 segundos
    setTimeout(() => {
        div.remove();
    }, 2000);
}

document.getElementById('numero').addEventListener('keydown', function(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        verificar();
    }
});