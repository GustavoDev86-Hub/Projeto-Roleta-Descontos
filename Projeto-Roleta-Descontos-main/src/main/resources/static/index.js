const API_URL = 'http://localhost:8080/roleta/girar';
const TEMPO_GIRO_MS = 3000;

let chancesRestantes = 3;
let dadosUsuario = null;

// Cupons dos descontos da roleta 
const TABELA_PREMIOS = {
    1: { valor: 3,  texto: "Você ganhou R$ 3 de desconto!",  cupom: "AVENIDA3" },
    2: { valor: 5,  texto: "Você ganhou R$ 5 de desconto!",  cupom: "AVENIDA5" },
    3: { valor: 10, texto: "Você ganhou R$ 10 de desconto!", cupom: "AVENIDA10" },
    4: { valor: 20, texto: "Você ganhou R$ 20 de desconto!", cupom: "AVENIDA20" },
    5: { valor: 40, texto: "Você ganhou R$ 40 de desconto!", cupom: "AVENIDA40" },
    6: { valor: 50, texto: "Você ganhou R$ 50 de desconto!", cupom: "AVENIDA50" }
};

const formLead = document.getElementById('form-lead');
const nomeInput = document.getElementById('nome');
const cpfInput = document.getElementById('cpf');
const telefoneInput = document.getElementById('telefone');
const emailInput = document.getElementById('email');
const btnGirar = document.getElementById('btn-girar');
const chancesElemento = document.getElementById('chances-restantes');
const modalResultado = document.getElementById('modal-resultado');
const textoPremio = document.getElementById('texto-premio');
const codigoCupom = document.getElementById('codigo-cupom');
const btnGirarNovamente = document.getElementById('btn-girar-novamente');
const btnResgatar = document.getElementById('btn-resgatar');

formLead.addEventListener('submit', async (event) => {
    event.preventDefault();

    if (chancesRestantes <= 0) {
        alert('Você já utilizou todas as suas tentativas!');
        return;
    }
    dadosUsuario = {
        nome: nomeInput.value.trim(),
        cpf: cpfInput.value.trim(),
        telefone: telefoneInput.value.trim(),
        email: emailInput.value.trim()
    };

    desativarCamposFormulario();

    await executarRodada();
});

// logica pra fazer o botao modal 
btnGirarNovamente.addEventListener('click', async () => {
    fecharModal();
    if (chancesRestantes > 0) {
        await executarRodada();
    }
});

btnResgatar.addEventListener('click', () => {
    alert(`Cupom ${codigoCupom.innerText} resgatado com sucesso!`);
    fecharModal();
});


async function executarRodada() {
    try {
        btnGirar.disabled = true;

        const idPremio = await solicitarGiroBackend(dadosUsuario);

        chancesRestantes--;
        atualizarContadorChances();

        await animarRoleta(idPremio);

        exibirModalPremio(idPremio);

    } catch (error) {
        console.error('Erro na rodada:', error);
        alert('Erro ao conectar com o servidor. Tente novamente.');
    } finally {
        if (chancesRestantes > 0) {
            btnGirar.disabled = false;
        }
    }
}
// pra fazer ligação com springboot
async function solicitarGiroBackend(dados) {
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nome: dados.nome,
            cpf: dados.cpf,
            telefone: dados.telefone,
            email: dados.email,
            tentativasRestantes: chancesRestantes 
        })
    });

    if (!response.ok) {
        throw new Error(`Erro no servidor: ${response.status}`);
    }

    return await response.json(); 
}

function animarRoleta(idPremio) {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(); 
        }, TEMPO_GIRO_MS);
    });
}

function atualizarContadorChances() {
    chancesElemento.innerText = chancesRestantes;
}

function desativarCamposFormulario() {
    nomeInput.disabled = true;
    cpfInput.disabled = true;
    telefoneInput.disabled = true;
    emailInput.disabled = true;
    document.getElementById('termos').disabled = true;
}

function exibirModalPremio(idPremio) {
    const premio = TABELA_PREMIOS[idPremio] || {
        texto: `Você ganhou o prêmio #${idPremio}!`,
        cupom: `CUPOM-${idPremio}`
    };

    textoPremio.innerText = premio.texto;
    codigoCupom.innerText = premio.cupom;

    if (chancesRestantes <= 0) {
        btnGirarNovamente.style.display = 'none';
        btnGirar.innerText = 'Tentativas Esgotadas';
        btnGirar.disabled = true;
    } else {
        btnGirarNovamente.style.display = 'inline-block';
    }

    modalResultado.classList.remove('modal-hidden');
}

function fecharModal() {
    modalResultado.classList.add('modal-hidden');
}