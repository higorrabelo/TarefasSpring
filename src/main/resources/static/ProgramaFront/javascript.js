
var titulo = document.getElementById('titulo');
var botao = document.getElementById('btn');
var clienteDiv="";

botao.addEventListener("click",function(){
    clienteDiv="";
    titulo.innerHTML = "Usuários Cadastrados"
    var ajax = new XMLHttpRequest()

    ajax.open("GET", "http://localhost:8080/cliente",true);

    ajax.onreadystatechange = function(){
        if(ajax.readyState == 4 && ajax.status==200){
            var dados = ajax.responseText;
            var dadosJson = JSON.parse(dados)
            dadosJson.forEach(element => {
                clienteDiv += `
                    <div class="container">
                    <h1>Nome Usuário: ${element.nome}</h1>
                    <h3>Código: ${element.id}</h3>
                    <p>Senha: ${element.senha}</p>
                    <p>Email: ${element.email}</p>
                    </div>
                `+'\n';
            });

            document.getElementById('conteudo').innerHTML = clienteDiv
        }/* else{
            clienteDiv = "<h1>Sem Conexão com o Servidor das Usuarios</h1>"
            document.getElementById('conteudo').innerHTML = clienteDiv
        } */
    }

    ajax.send()

})

function mostrarTarefas(){
    clienteDiv="";
    titulo.innerHTML = "Tarefas Cadastradas"
    var ajax = new XMLHttpRequest()
    ajax.open("GET", "http://localhost:8080/tarefas",true);

        ajax.onreadystatechange = function(){
            if(ajax.readyState == 4 && ajax.status==200){
                var dados = ajax.responseText;
                var dadosJson = JSON.parse(dados)
                //console.log(dadosJson);
                dadosJson.forEach(element => {
                    clienteDiv += `
                        <div class="container">
                        <h1>Título: ${element.titulo}</h1>
                        <h3>Código: ${element.id}</h3>
                        <p>Descrição: ${element.descricao}</p>
                        <p>Data Cadastro: ${element.dataCadastro}</p>
                        <p>Usuario id: ${element.dataCadastro}</p>
                        </div>
                    `+'\n';
                });

                document.getElementById('conteudo').innerHTML = clienteDiv
            }/* else{
                clienteDiv = "<h1>Sem Conexão com o Servidor das Tarefas</h1>"
                document.getElementById('conteudo').innerHTML = clienteDiv
            } */
        
    }
    ajax.send()
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

    var usuario = document.getElementById('usuario')

usuario.addEventListener("submit",(evento)=>{
    evento.preventDefault()
})

}


