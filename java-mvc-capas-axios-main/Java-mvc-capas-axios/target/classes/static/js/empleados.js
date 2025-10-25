const urlEmpleados = "http://localhost:8080/api/empleados";
const urlDepartamentos = "http://localhost:8080/api/departamentos";

const form = document.getElementById("formEmpleado");
const tabla = document.getElementById("tablaEmpleados");
const btnNuevo = document.getElementById("btnNuevo");
const btnGuardar = document.getElementById("btnGuardar");
const btnCancelar = document.getElementById("btnCancelar");
const selectDepto = document.getElementById("departamento");

// Cerrar sesión
document.getElementById("logout").addEventListener("click", () => {
    localStorage.removeItem("usuario");
    window.location.href = "index.html";
});

// Si no hay login, redirigir
if (!localStorage.getItem("usuario")) {
    window.location.href = "index.html";
}

// Cargar empleados y departamentos
window.addEventListener("DOMContentLoaded", () => {
    cargarDepartamentos();
    listarEmpleados();
});

// Mostrar formulario nuevo empleado
btnNuevo.addEventListener("click", () => {
    form.classList.remove("d-none");
    document.getElementById("tituloForm").textContent = "Nuevo Empleado";
    document.getElementById("empleadoId").value = "";
    form.reset();
});

// Cancelar
btnCancelar.addEventListener("click", () => form.classList.add("d-none"));

// Guardar empleado (crear o actualizar)
btnGuardar.addEventListener("click", async () => {
    const empleado = {
        nombre: document.getElementById("nombre").value,
        apellido: document.getElementById("apellido").value,
        email: document.getElementById("email").value,
        telefono: document.getElementById("telefono").value,
        fechaContratacion: document.getElementById("fechaContratacion").value,
        salario: document.getElementById("salario").value,
        cargo: document.getElementById("cargo").value,
        departamento: { id: selectDepto.value }
    };

    const id = document.getElementById("empleadoId").value;

    try {
        if (id) {
            await axios.put(`${urlEmpleados}/${id}`, empleado);
            alert("Empleado actualizado");
        } else {
            await axios.post(urlEmpleados, empleado);
            alert("Empleado creado");
        }
        listarEmpleados();
        form.classList.add("d-none");
    } catch (error) {
        alert("Error al guardar empleado");
    }
});

// Listar empleados
async function listarEmpleados() {
    const res = await axios.get(urlEmpleados);
    tabla.innerHTML = "";
    res.data.forEach(emp => {
        tabla.innerHTML += `
      <tr>
        <td>${emp.nombre}</td>
        <td>${emp.apellido}</td>
        <td>${emp.email}</td>
        <td>${emp.cargo}</td>
        <td>${emp.salario}</td>
        <td>${emp.departamento ? emp.departamento.nombre : "-"}</td>
        <td>
          <button class="btn btn-sm btn-warning" onclick="editarEmpleado(${emp.id})">Editar</button>
          <button class="btn btn-sm btn-danger" onclick="eliminarEmpleado(${emp.id})">Eliminar</button>
        </td>
      </tr>`;
    });
}

// Cargar departamentos
async function cargarDepartamentos() {
    const res = await axios.get(urlDepartamentos);
    selectDepto.innerHTML = res.data.map(d => `<option value="${d.id}">${d.nombre}</option>`).join("");
}

// Editar
async function editarEmpleado(id) {
    const res = await axios.get(`${urlEmpleados}/${id}`);
    const emp = res.data;
    document.getElementById("tituloForm").textContent = "Editar Empleado";
    form.classList.remove("d-none");
    document.getElementById("empleadoId").value = emp.id;
    document.getElementById("nombre").value = emp.nombre;
    document.getElementById("apellido").value = emp.apellido;
    document.getElementById("email").value = emp.email;
    document.getElementById("telefono").value = emp.telefono;
    document.getElementById("fechaContratacion").value = emp.fechaContratacion;
    document.getElementById("salario").value = emp.salario;
    document.getElementById("cargo").value = emp.cargo;
    selectDepto.value = emp.departamento ? emp.departamento.id : "";
}

// Eliminar
async function eliminarEmpleado(id) {
    if (confirm("¿Seguro que deseas eliminar este empleado?")) {
        await axios.delete(`${urlEmpleados}/${id}`);
        listarEmpleados();
    }
}
