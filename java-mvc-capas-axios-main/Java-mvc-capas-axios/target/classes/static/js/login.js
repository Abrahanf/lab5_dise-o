document.getElementById("btnLogin").addEventListener("click", async () => {
    const login = document.getElementById("login").value.trim();
    const clave = document.getElementById("clave").value.trim();
    const mensaje = document.getElementById("mensaje");

    try {
        const response = await axios.post("http://localhost:8080/api/usuarios/login", {
            login,
            clave
        });
        alert(" Bien" + response.data);
        // Guardar estado de login (simple, sin JWT)
        localStorage.setItem("usuario", login);
        window.location.href = "panel.html";
    } catch (error) {
        mensaje.textContent = "Usuario o contraseña incorrectos";
    }
});
