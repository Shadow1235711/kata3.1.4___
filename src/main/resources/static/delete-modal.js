let formDelete = document.forms["formDelete"]
deleteUser();

async function deleteModal(id) {
    const modalDelete = new bootstrap.Modal(document.querySelector('#deleteModal'));
    await open_fill_modal(formDelete, modalDelete, id);
    loadRolesForDelete(id);
}

function deleteUser() {
    formDelete.addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/api/admin/users/" + formDelete.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#deleteClose').click();
            getAllUsers();
        });
    });
}

//Приведение ролей к виду JS
function loadRolesForDelete(userId) {
    let selectDelete = document.getElementById("delete-roles");
    selectDelete.innerHTML = "";

    fetch(`http://localhost:8080/api/admin/roles/${userId}`)
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.name.toString().replace('ROLE_', '');
                selectDelete.appendChild(option);
            });
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesForDelete);







