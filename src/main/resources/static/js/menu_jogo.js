
async function dadosPlayer(){

    try{

        const resposta = await fetch("/player/get-player",{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                idPlayer: "3d0004a3-82aa-417a-908a-36e7863afe8f"
            })
        });

        if (!resposta.ok) {
            throw new Error("HTTP "+ resposta.status);
        }
        console.log(resposta);

        const player = await resposta.json();
        atualizandoDadosBar(player)

        document.getElementById("vida").textContent = `Vida: ${player.vida}/ 100`;
        document.getElementById("dinheiro").textContent = `Dinheiro: ${player.moeda}`;
        document.getElementById("rank").textContent = `Rank: ${player.player_rank}`;


    }catch (error) {
        console.log(error);
        alert(error.message);
    }


}

function atualizandoDadosBar(player){

    const somaXp = (player.xp / 100) * 100;
    document.getElementById("barra-xp").style.width = `${somaXp}%`;



    const porcentagem = (player.vida / 100) * 100;

    document.getElementById("vida").textContent = `Vida: ${player.vida}`;
    document.getElementById("barra-vida").style.width = `${porcentagem}%`;


    document.getElementById("card-player-vida").textContent = `${player.vida}/ 100`;
    document.getElementById("card-player-dano").textContent = `${player.dano}`;
    document.getElementById("card-player-defesa").textContent = `${player.defesa}`;
    document.getElementById("card-player-rank").textContent = `${player.player_rank}`;

}
dadosPlayer()