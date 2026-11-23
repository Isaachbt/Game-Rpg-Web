const telas = {
    floresta: document.getElementById("tela-floresta"),
    procurando: document.getElementById("tela-procurando"),
    vazio: document.getElementById("tela-vazio"),
    encontro: document.getElementById("tela-encontro"),
    batalha: document.getElementById("tela-batalha"),
    vitoria: document.getElementById("tela-vitoria"),
    derrota: document.getElementById("tela-derrota")
};

let idPlayer = "3d0004a3-82aa-417a-908a-36e7863afe8f";
let monstro = false;
let bloqueado = false;
let player = false;
let batalha  = false;



async function iniciarJogo() {
    await dadosPlayer();
}

async function getMonstro(){
    try{
        const resposta = await fetch("/batalha/batalhaEncontrada",{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                idPlayer: idPlayer
            })
        });

        if (!resposta.ok) {
            throw new Error("Erro ao buscar monstro: HTTP "+ resposta.status);
        }
        batalha = await resposta.json();
        console.log(batalha);
        monstro = {...batalha.monstro};
    }catch (error) {
        console.log(error);
        alert(error.message);
    }
}

async function dadosPlayer(){
    try{
        const resposta = await fetch("/player/get-player",{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                idPlayer: idPlayer
            })
        });

        if (!resposta.ok) {
            throw new Error("Erro ao buscar player: HTTP "+ resposta.status);
        }
        player = await resposta.json();
        atualizarTopo()

    }catch (error) {
        console.log(error);
        alert(error.message);
    }
}

function mostrarTela(nome) {
    Object.values(telas).forEach(tela => tela.classList.remove("active"));
    telas[nome].classList.add("active");
}

function esperar(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function limitar(valor, min, max) {
    return Math.max(min, Math.min(max, valor));
}

function atualizarBarra(elemento, atual, maximo) {
    const porcentagem = limitar((atual / maximo) * 100, 0, 100);
    elemento.style.width = `${porcentagem}%`;
}

function atualizarTopo() {

    console.log("top",player)
    document.getElementById("top-vida").textContent =
        `Vida: ${player.vida}/ 100`;

    document.getElementById("top-moeda").textContent =
        `Dinheiro: ${player.moeda}`;

    document.getElementById("top-rank").textContent =
        `Rank: ${player.player_rank}`;

    atualizarBarra(document.getElementById("top-barra-rank"), player.xp,100)

    atualizarBarra(document.getElementById("top-barra-vida"),
        player.vida,
        100
    );
}

function atualizarBatalha() {
    document.getElementById("player-nome").textContent = player.nomePlayer;
    document.getElementById("player-vida-texto").textContent = `${player.vida}/100`;
    document.getElementById("player-dano").textContent = player.dano;
    document.getElementById("player-defesa").textContent = player.defesa;

    document.getElementById("monstro-nome").textContent = monstro.nome;
    document.getElementById("monstro-vida-texto").textContent = `${batalha.vidaAtualMonstro}/${monstro.vida}`;
    document.getElementById("monstro-dano").textContent = monstro.dano;
    document.getElementById("monstro-defesa").textContent = monstro.defesa;

    atualizarBarra(document.getElementById("player-barra-vida"),
        player.vida, 100);

    atualizarBarra(document.getElementById("monstro-barra-vida"),
        batalha.vidaAtualMonstro, monstro.vida);

    atualizarTopo();
}

function adicionarLog(texto, tipo = "system") {
    const log = document.getElementById("log-batalha");
    const linha = document.createElement("p");

    linha.className = `log-${tipo}`;
    linha.textContent = texto;

    log.appendChild(linha);
    log.scrollTop = log.scrollHeight;
}

function limparLog() {
    document.getElementById("log-batalha").innerHTML =
        '<p class="log-system">Log limpo.</p>';
}

function bloquearAcoes(valor) {
    bloqueado = valor;

    document.querySelectorAll(".skill-card").forEach(btn => {
        btn.disabled = valor;
    });
}

async function procurarNaFloresta() {
    mostrarTela("procurando");
    await esperar(2000);

    const sorte = Math.random();

    if (sorte < 0.35) {
        mostrarTela("vazio");
        await esperar(2000);
        mostrarTela("floresta");
        return;
    }
    await getMonstro();

    monstro = {...batalha.monstro};
    document.getElementById("encontro-monstro-nome").textContent = monstro.nome;
    mostrarTela("encontro");
}

function iniciarBatalha() {
    monstro = {...batalha.monstro};
    document.getElementById("log-batalha").innerHTML =
        `<p class="log-system">${monstro.nome} apareceu!</p>`;

    atualizarBatalha();
    mostrarTela("batalha");
}

async function ataqueMonstro() {

    await esperar(200);

    adicionarLog(`${monstro.nome} causou ${batalha.danoCausadoMonstro} de dano.`, "enemy");
    atualizarBatalha();

    if (player.vida <= 0) {
        await esperar(700);
        bloquearAcoes(true);
        mostrarTela("derrota");
        return;
    }
}

async function atacando(){
    if (bloqueado) return;

    bloquearAcoes(true);

    try{
        const resposta = await fetch("/batalha/atacando",{
            method: "POST",
            headers: {"Content-Type": "application/json",},
            body: JSON.stringify({
                idBatalha: batalha.idBatalha
            })
        });

        if (!resposta.ok) {
            throw new Error("Erro ao buscar batalha: HTTP "+ resposta.status);
        }
        batalha = null;
        batalha = await resposta.json();
        monstro = {...batalha.monstro};
        player = {...batalha.player}
    }catch (error) {
        console.log(error);
        alert(error.message);
    }

    adicionarLog(`${player.nomePlayer} causou ${batalha.danoCausadoPlayer} de dano.`, "player");
    atualizarBatalha();
    if (batalha.vidaAtualMonstro <= 0) {
        await vencerBatalha();
        return;
    }

    await ataqueMonstro()
    bloquearAcoes(false);
}

async function usarAtaque() {
    if (bloqueado) return;

    bloquearAcoes(true);

    const dano = Math.max(player.dano - monstro.defesa, 1);
    monstro.vida = limitar(monstro.vida - dano, 0, monstro.vida);

    adicionarLog(`${player.nomePlayer} causou ${dano} de dano.`, "player");
    atualizarBatalha();

    if (monstro.vida <= 0) {
        await vencerBatalha();
        return;
    }

    await ataqueMonstro();
    bloquearAcoes(false);
}

async function usarEspecial() {
    if (bloqueado) return;

    bloquearAcoes(true);

    const danoBruto = Math.round(player.dano * 1.7);
    const dano = Math.max(danoBruto - monstro.defesa, 1);

    monstro.vida = limitar(monstro.vida - dano, 0,100);

    adicionarLog(`Golpe Especial causou ${dano} de dano.`, "player");
    atualizarBatalha();

    if (monstro.vida <= 0) {
        await vencerBatalha();
        return;
    }

    await ataqueMonstro();
    bloquearAcoes(false);
}

async function usarPocao() {
    if (bloqueado) return;

    if (player.pocoes <= 0) {
        adicionarLog("Você não possui mais poções.", "system");
        return;
    }

    if (player.vida >= player.vidaMax) {
        adicionarLog("Sua vida já está cheia.", "system");
        return;
    }

    bloquearAcoes(true);

    player.pocoes--;
    const vidaAntes = player.vida;
    player.vida = limitar(player.vida + 30, 0, 100);
    const curado = player.vida - vidaAntes;

    adicionarLog(`Você recuperou ${curado} de vida.`, "heal");
    atualizarBatalha();

    await ataqueMonstro();
    bloquearAcoes(false);
}

async function tentarFugir() {
    if (bloqueado) return;

    bloquearAcoes(true);

    const conseguiu = Math.random() < 0.55;

    if (conseguiu) {
        adicionarLog("Você conseguiu fugir da batalha.", "system");
        await esperar(700);
        mostrarTela("floresta");
        bloquearAcoes(false);
        return;
    }

    adicionarLog("Você tentou fugir, mas falhou.", "system");
    await ataqueMonstro();
    bloquearAcoes(false);
}

async function vencerBatalha() {
    adicionarLog(`${monstro.nome} foi derrotado!`, "system");
    adicionarLog(`${player.nome} ganhou ${monstro.xp} de xp!`, "system");

    document.getElementById("recompensa-xp").textContent =
        `+${monstro.xp} XP`;

    document.getElementById("recompensa-moeda").textContent =
        `+${batalha.moedasGanha} moedas`;

    atualizarTopo();

    await esperar(900);
    mostrarTela("vitoria");
    bloquearAcoes(false);
}

document.getElementById("btn-procurar").addEventListener("click", procurarNaFloresta);
document.getElementById("btn-iniciar-batalha").addEventListener("click", iniciarBatalha);
document.getElementById("btn-fugir-encontro").addEventListener("click", () => mostrarTela("floresta"));

document.getElementById("skill-ataque").addEventListener("click", atacando);
document.getElementById("skill-especial").addEventListener("click", usarEspecial);
document.getElementById("skill-pocao").addEventListener("click", usarPocao);
document.getElementById("skill-fugir").addEventListener("click", tentarFugir);

document.getElementById("btn-limpar-log").addEventListener("click", limparLog);
document.getElementById("btn-voltar-vitoria").addEventListener("click", () => mostrarTela("floresta"));
document.getElementById("btn-voltar-derrota").addEventListener("click", () => {
    atualizarTopo();
    window.location.href = "../floresta-inicio.html";
});
document.getElementById("voltar-para-menu").addEventListener("click", () =>{
    window.location.href = "../floresta-inicio.html";
})

iniciarJogo()
