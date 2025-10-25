 function verificar() {
      const valor = document.getElementById('numero').value;
      const numero = Number(valor);
      const alerta = document.getElementById('alerta');
      alerta.innerHTML = ""; //

      if (!valor) {
        alerta.innerHTML = `
          <div class="alert alert-warning" role="alert">
            Por favor, ingresa un número.
          </div>`;
      } else if (!Number.isInteger(numero)) {
        alerta.innerHTML = `
          <div class="alert alert-danger" role="alert">
            Solo se permiten números enteros.
          </div>`;
      } else {
        alerta.innerHTML = `
          <div class="alert alert-success" role="alert">
            Número válido: ${numero}
          </div>`;
      }
    }