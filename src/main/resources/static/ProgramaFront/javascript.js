const formulario = document.querySelector('form');
var titulo = document.getElementById('titulo');
var botao = document.getElementById('btn');
var clienteDiv="";
var htmlString = "";
const selection = document.querySelector('#selection');



carregaLista()

function carregaLista(){
    var ajax = new XMLHttpRequest()
            ajax.open("GET", "http://localhost:8080/cliente");

            ajax.onreadystatechange =  function(){
                if(ajax.readyState == 4 && ajax.status==200){
                    var dados =  ajax.responseText;
                    var dadosJson =  JSON.parse(dados)
                    dadosJson.forEach( function(elemento){
                        htmlString+=  `<option value="${elemento.id}">ID: ${elemento.id} -${elemento.nome}</option>\n`
                    })
                    console.log(htmlString);
                    selection.innerHTML = htmlString;
                }
            }
            ajax.send();
}

function mostrarCLientes(){
    clienteDiv="";
    titulo.innerHTML = "Usuários Cadastrados"
    var ajax = new XMLHttpRequest()

    ajax.open("GET", "http://localhost:8080/cliente",true);

    ajax.onreadystatechange = function(){
        if(ajax.readyState == 4 && ajax.status==200){
            var dados = ajax.responseText;
            var dadosJson = JSON.parse(dados)
            clientesJson = dadosJson;
            dadosJson.forEach(element => {
                clienteDiv += `
                    <div class="container">
                    <h4>Nome Usuário: ${element.nome}</h4>
                    <h5>Código: ${element.id}</h5>
                    <p>Senha: ${element.senha}</p>
                    <p>Email: ${element.email}</p>
                    <button id="btnRemover" onclick="apagarRegistro(${element.id})">Remover</button>
                    </div>
                `+'\n';
            });

            document.getElementById('conteudo').innerHTML = clienteDiv
        }
    }
    ajax.send();
}



function mostrarTarefas(){
    clienteDiv="";
    titulo.innerHTML = "Tarefas Cadastradas"
    var ajax = new XMLHttpRequest();
   
    ajax.open("GET", "http://localhost:8080/tarefas",true);

        ajax.onreadystatechange = function(){
            if(ajax.readyState == 4 && ajax.status==200){
                var dados = ajax.responseText;
                var dadosJson = JSON.parse(dados);
                console.log(dadosJson)
                 if(dadosJson!=null){
                    dadosJson.forEach(element => {
                        clienteDiv += `
                            <div class="container">
                            <h4>Título: ${element.titulo}</h4>
                            <h5>Código: ${element.id}</h5>
                            <p>Descrição: ${element.descricao}</p>
                            <p>Data Cadastro: ${element.dataCadastro}</p>
                            <p>Data Entrega: ${element.dataEntrega}</p>
                            <p>Usuario id: ${element.cliente_id}</p>
                            <button id="btnRemover" onclick="apagarTarefa(${element.id})">Remover</button>
                            </div>
                        `+'\n';
                    });
                 }else{
                    console.log("Nada pra mostrar");
                 }
                document.getElementById('conteudo').innerHTML = clienteDiv
            }    
    }
    ajax.send();
}


function formCadastro(){
    var clienteDiv="";
    
    titulo.innerHTML = "Cadastro de usuário"
    var ajax = new XMLHttpRequest()
    ajax.open("GET",'usuario.html',true);

    ajax.onload = function(){
        if(ajax.status==200){
            var data = ajax.responseText;
            clienteDiv = data;
            document.getElementById('conteudo').innerHTML = clienteDiv;
        }
    }
    ajax.send();

}

function formtarefa(){
    var clienteDiv="";
    
    titulo.innerHTML = "Cadastro de Tarefa"
    var ajax = new XMLHttpRequest()
    ajax.open("GET",'tarefas.html',true);

    ajax.onload = function(){
        if(ajax.status==200){
            var data = ajax.responseText;
            clienteDiv = data;
            document.getElementById('conteudo').innerHTML = clienteDiv;
        }
    }
    ajax.send();

}


function cadastrarCliente() {
    const form = document.querySelector("#formUsuario");
    form.addEventListener("submit",function(event){
        event.preventDefault();
        const nomeValue = document.getElementById("nome").value;
        const senhaValue = document.getElementById("senha").value;
        const emailValue = document.getElementById("email").value;

        fetch("http://localhost:8080/cliente", {
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            method: "POST",
            body: JSON.stringify({
                nome: nomeValue,
                senha: senhaValue,
                email: emailValue
            })
        })
        .then(function (res) {
            if (res.ok) {
                // A resposta do servidor foi bem-sucedida (status 2xx)
                return res.json(); // Você pode processar a resposta JSON aqui
            } else {
                // A resposta do servidor não foi bem-sucedida (status não 2xx)
                throw new Error("Erro na solicitação");
            }
        })
        .then(function (data) {
            // Faça algo com os dados da resposta JSON, se necessário
            console.log(data);
            alert("Cadastro bem-sucedido");
        })
        .catch(function (error) {
            console.log(error);
            alert("Erro ao cadastrar");
        });
    })
}

function apagarRegistro(id){

    fetch(`http://localhost:8080/cliente/${id}`,{
        headers: {
            "Accept":"application/json",
            "Content-Type":"application/json"
        },
        method:"DELETE",

    }).then(()=>{
        alert("Usuario Removido com sucesso");
        mostrarCLientes()
    }).catch((erro)=>{
        console.log(erro);
    });

    
}
function apagarTarefa(id){

    fetch(`http://localhost:8080/tarefas/${id}`,{
        headers: {
            "Accept":"application/json",
            "Content-Type":"application/json"
        },
        method:"DELETE",

    }).then(()=>{
        alert("Tarefa Removida com sucesso");
        mostrarTarefas()
       }
        
    ).catch((erro)=>{
        console.log(erro);
    });

}


function cadastrarTarefa(){
    const tarefaform = document.querySelector('#tarefaForm');

tarefaform.addEventListener("submit",function(event){
    const titulos = document.getElementById('titulos');
    const descricao = document.getElementById('descricao');
    const dataEntrega = document.getElementById('dataEntrega');
    const cliente_id = document.getElementById('cliente_id');
    event.preventDefault();
    fetch(`http://localhost:8080/tarefas`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST",
        body:JSON.stringify({
        titulo:titulos.value,
        descricao:descricao.value,
        dataEntrega:dataEntrega,
        cliente_id:cliente_id.value
    })
    }) .then(function (res) {
        if (res.ok) {
            // A resposta do servidor foi bem-sucedida (status 2xx)
            return res.json(); // Você pode processar a resposta JSON aqui
        } else {
            // A resposta do servidor não foi bem-sucedida (status não 2xx)
            throw new Error("Erro na solicitação");
        }
    })
    .then(function (data) {
        // Faça algo com os dados da resposta JSON, se necessário
        console.log(data);
        alert("Cadastro bem-sucedido");
    })
    .catch(function (error) {
        console.log(error);
        alert("Erro ao cadastrar");
    });
})


}




function buscaPorNome(){
    const busca = document.getElementById('busca');
    const btnBusca = document.getElementById('btnBusca');

    var url = "http://localhost:8080/cliente/buscar/"+busca.value.charAt(0).toUpperCase();

    fetch(url)
  .then(function (response) {
    console.log(response)
    if (response.ok) {
      // A requisição foi bem-sucedida
      return response.json();
    } else {
      // A requisição falhou
      throw new Error('Erro na requisição: ' + response.status);
    }
  })
  .then(function (data) {
    clienteDiv="";
    console.log(data)
        data.forEach(element=>{
            clienteDiv += `
            <div class="container">
            <h4>Nome Usuário: ${element.nome}</h4>
            <h5>Código: ${element.id}</h5>
            <p>Senha: ${element.senha}</p>
            <p>Email: ${element.email}</p>
            <button id="btnRemover" onclick="apagarRegistro(${element.id})">Remover</button>
            </div>
        `+'\n';
        })
    
    document.getElementById('conteudo').innerHTML = clienteDiv
  })
  .catch(function (error) {
    // Lide com erros
    console.error(error);
  });
}