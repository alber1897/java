const addClient = document.getElementById("newClient");
const listClient = document.getElementById("listClient");
const searchClient = document.getElementById("searchClient");

const addClientButton = document.getElementById("addClientButton");
const listClientButton = document.getElementById("listClientButton");
const searchClientButton = document.getElementById("searchClientButton");


addClientButton.addEventListener("click", (e)=>{
    e.preventDefault();
    addClient.style.display = "block";
    addClientButton.className = "active";

    listClient.style.display = "none";
    listClientButton.className = "";

    searchClient.style.display = "none";
    searchClientButton.className = "";
});

listClientButton.addEventListener("click", (e)=>{
    e.preventDefault();
    addClient.style.display = "none";
    addClientButton.className = "";

    listClient.style.display = "block";
    listClientButton.className = "active";

    searchClient.style.display = "none";
    searchClientButton.className = "";
});

searchClientButton.addEventListener("click", (e)=>{
    e.preventDefault();
    addClient.style.display = "none";
    addClientButton.className = "";

    listClient.style.display = "none";
    listClientButton.className = "";

    searchClient.style.display = "block";
    searchClientButton.className = "active";
});