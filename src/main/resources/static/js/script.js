function mostrarTela(id) {
    const telas = document.querySelectorAll(".tela");

    telas.forEach(tela => {
        tela.style.display = "none";
    })

    document.getElementById(id).style.display = "flex";
}