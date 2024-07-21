

document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(document.getElementById('loginForm'));

    fetch('/index/login', { 
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(error => { throw new Error(error.message); });
        }
        return response.json();
    })
    .then(data => {
        if (data.status === "success") {
            window.location.href = data.redirect;
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        alert( error.message);
        console.error('Error:', error);
    });
});


document.getElementById('register').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(document.getElementById('register'));

    fetch('/index/register', { 
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(error => { throw new Error(error.message); });
        }
        return response.json();
    })
    .then(data => {
        alert(data.message);
        if (data.status === "success") {
            var createModal = new bootstrap.Modal(document.getElementById('registerModal'));
            createModal.hide();
            location.reload(); 
        }
    })
    .catch(error => {
        alert(error.message);
        console.error('Error:', error);
    });
});



