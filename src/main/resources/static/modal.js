async function getUserById(id) {
    let response = await fetch("http://localhost:8080/api/admin/users/" + id);
    return await response.json();
}

async function open_fill_modal(form, modal, id) {
    modal.show();
    let user = await getUserById(id);
    form.id.value = user.id;
    form.name.value = user.name;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.email.value = user.email;
    form.password.value = user.password;
    form.roles.value = user.roles;
}