const URLTableUsers = 'http://localhost:8080/api/admin/users/';

getAllUsers();

function getAllUsers() {
    fetch(URLTableUsers)
        .then(function (response) {
            return response.json();
        })
        .then(function (users) {
            let dataOfUsers = '';
            let rolesString = '';

            const tableUsers = document.getElementById('tableUsers');

            for (let user of users) {

                rolesString = rolesToString(user.roles);

                dataOfUsers += `<tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${rolesString}</td>


                        <td>
                          <button type="button"
                          class="btn btn-info"
                          data-bs-toogle="modal"
                          data-bs-target="#editModal"
                          onclick="editModal(${user.id})">
                                Edit
                            </button>
                        </td>


                        <td>
                            <button type="button" 
                            class="btn btn-danger" 
                            data-toggle="modal" 
                            data-target="#deleteModal" 
                            onclick="deleteModal(${user.id})">
                                Delete
                            </button>
                        </td>
                    </tr>`;
            }
            tableUsers.innerHTML = dataOfUsers;
        })
}

function rolesToString(roles) {
    let rolesString = '';
    for (const element of roles) {
        rolesString += (element.name.toString().replace('ROLE_', '') + ', ');
    }
    rolesString = rolesString.substring(0, rolesString.length - 2);
    return rolesString;
}