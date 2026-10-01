let classeEscolhida = null;
async function carregarClasses() {

    const lista = document.getElementById("lista_classes");

    try {

        const resposta = await fetch("/game/listClass");

        if (!resposta.ok) {
            throw new Error("HTTP " + resposta.status);
        }

        const classes = await resposta.json();

        lista.innerHTML = "";

        classes.forEach(classe => {

            const caminhoImagem = `/img/${classe.nome.toLowerCase()}.png`;
            const card = document.createElement("div");
            card.classList.add("card_classe");

            card.innerHTML = `
                <img
                    src="${caminhoImagem}"
                    alt="${classe.nome}"
                >
                <h2>${classe.nome}</h2>
                <p>Vida: ${classe.vida}</p>
                <p>Dano: ${classe.dano}</p>
                <p>Defesa: ${classe.defesa}</p>
            `;

            card.addEventListener("click", () => {

                document.querySelectorAll(".card_classe")
                    .forEach(card => card.classList.remove("selecionado"));

                card.classList.add("selecionado");

                classeEscolhida = classe;
            });
            lista.appendChild(card);
        });

    } catch (erro) {
        console.error(erro);
        lista.innerHTML = `<p>Erro ao carregar classes: ${erro.message}</p>`;
    }

}

function validarBtnConfirmarPlayer(){

        const btnCriarPlayer = document.getElementById("btn_criar-jogador");

        btnCriarPlayer.addEventListener("click", () => {
            const nomeJogador = document.getElementById("input-nome").value.trim();

            if (nomeJogador.trim() === "") {
                alert("Digite seu nome!");
                return;
            }

            if (!classeEscolhida) {
                alert("Selecione uma class!")
                return;
            }

            salvandoPlayerNoBanco(nomeJogador);
        })
}

async function salvandoPlayerNoBanco(nomeJogador){

    console.log(classeEscolhida.idclasses)
    console.log(nomeJogador)
    const jogador = {
        nomePlayer: nomeJogador,
        idclasses: classeEscolhida.idclasses
    }

    try{
        const resposta = await fetch("/player/salvandoPlayer", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(jogador)
        });

        if (!resposta.ok) {
            throw new Error("HTTP " + resposta.status);
        }
        alert("Jogador criado.");

    }catch (erro) {
        console.error("Erro ao criar jogador:", erro);
        alert("Não foi possível criar o jogador.");
    }

}

carregarClasses();
validarBtnConfirmarPlayer()