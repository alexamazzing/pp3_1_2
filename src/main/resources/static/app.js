const url = 'http://localhost:8080/api/users'
const urlRoles = 'http://localhost:8080/api/roles'
const usersList = document.querySelector('.usersList')
let result = ''

fetch(url)
    .then(response => response.json())
    .then(data => {
        drawTable(data)
        console.log(data)
    })
    .catch(err => console.log(err))

const drawTable = (users) => {
    users.forEach( user => {
        let userRoles = ``
        for(let role of user.roles) {
            userRoles += role.name.replace("ROLE_", "") + ' '
        }
        result += `<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${userRoles}</td>
                    <td><button type="button" class="btn btn-info text-white editButton" data-bs-toggle="modal">Edit</button></td>
                    <td><button type="button" class="btn btn-danger text-white deleteButton" data-bs-toggle="modal">Delete</button></td>
                   </tr>`

    })
    usersList.innerHTML = result
}

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}


const editModal = new bootstrap.Modal(document.getElementById('editModal'))
const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'))

on(document, 'click', '.editButton', e => {
    const tableRow = e.target.parentNode.parentNode
    editIdForm = tableRow.children[0].innerHTML

    fetch(url +'/' + editIdForm)
        .then(res => res.json())
        .then(data => showEditModal(data))
})

on(document, 'click', '.deleteButton', e => {
    const tableRow = e.target.parentNode.parentNode
    deleteIdForm = tableRow.children[0].innerHTML

    fetch(url +'/' + deleteIdForm)
        .then(res => res.json())
        .then(data => showDeleteModal(data))
})

const showEditModal = (user) => {
    const editId = document.getElementById('edit_id_input')
    const editName = document.getElementById('edit_name_input')
    const editSurname = document.getElementById('edit_surname_input')
    const editAge = document.getElementById('edit_age_input')
    const editEmail = document.getElementById('edit_email_input')
    const editRoles = document.getElementById('edit_roles_select')

    editId.value = user.id
    editName.value = user.name
    editSurname.value = user.surname
    editAge.value = user.age
    editEmail.value = user.email
    while (editRoles.options.length) {
        editRoles.options[0] = null;
    }
    fetch(urlRoles)
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                user.roles.forEach(userRole => {
                    if (userRole.id === role.id) {
                        editRoles.append(new Option(role.name.replace("ROLE_", ""), role.id, true, true));
                        role = true;
                    }
                });
                (role !== true) ? editRoles.append(new Option(role.name.replace("ROLE_", ""), role.id)) : null;
            });
        });

    editModal.show()
}

const showDeleteModal = (user) => {
    const deleteId = document.getElementById('delete_id_input')
    const deleteName = document.getElementById('delete_name_input')
    const deleteSurname = document.getElementById('delete_surname_input')
    const deleteAge = document.getElementById('delete_age_input')
    const deleteEmail = document.getElementById('delete_email_input')
    const deleteRoles = document.getElementById('delete_roles_select')

    deleteId.value = user.id
    deleteName.value = user.name
    deleteSurname.value = user.surname
    deleteAge.value = user.age
    deleteEmail.value = user.email
    while (deleteRoles.options.length) {
        deleteRoles.options[0] = null;
    }
    fetch(urlRoles)
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                user.roles.forEach(userRole => {
                    if (userRole.id === role.id) {
                        deleteRoles.append(new Option(role.name.replace("ROLE_", ""), role.id, true, true));
                        role = true;
                    }
                });
                (role !== true) ? deleteRoles.append(new Option(role.name.replace("ROLE_", ""), role.id)) : null;
            });
        });

    deleteModal.show()
}

const name = document.getElementById('name_input')
const surname = document.getElementById('surname_input')
const age = document.getElementById('age_input')
const email = document.getElementById('email_input')
const password = document.getElementById('password_input')
const formRoles = document.getElementById('roles')
fetch(urlRoles)
    .then(res => res.json())
    .then(roles => {
        roles.forEach(role => {
            formRoles.append(new Option(role.name.replace("ROLE_", ""), role.id + ''));
        })
    })



const addUserButton = document.querySelector('#addUser')

addUserButton.addEventListener('click',
    e => {
        e.preventDefault()

        let rolesFromForm = document.getElementById('roles')
        let selectedOptions = rolesFromForm.selectedOptions
        let selected = []
        for (let i=0; i < selectedOptions.length; i++) {
            selected.push(selectedOptions[i].value)
        }
        let userRoles = new Array()

        fetch(urlRoles)
            .then(res => res.json())
            .then(roles => {
            roles.forEach(role => {
                selected.forEach(roleId => {
                    if (role.id == roleId) {
                        userRoles.push(role);
                    }
                });
            });
        });
        console.log(userRoles)
        let data = JSON.stringify({
            name: name.value,
            surname: surname.value,
            age: age.value,
            email: email.value,
            password: password.value,
            roles: userRoles
        })
        console.log(data)
        // fetch(url, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     body: data
        // })
        //     .then(res => res.json())
        //     .then(data => {
        //         const newUser = []
        //         newUser.push(data)
        //         drawTable(newUser)
        //     })
        name.value = ''
        surname.value = ''
        age.value = ''
        email.value = ''
        password.value = ''
        formRoles.value = ''
    })

/*const deleteUserButton = document.querySelector('')
addUserButton.addEventListener('click', e => {
    e.preventDefault()
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            //TODO
        })
    })
        .then(res => res.json())
        .then(data => {
            const newUser= []
            newUser.push(data)
            drawTable(newUser)
        })

})
const editUserButton = document.querySelector('')
addUserButton.addEventListener('click', e => {
    e.preventDefault()
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            //TODO
        })
    })
        .then(res => res.json())
        .then(data => {
            const newUser= []
            newUser.push(data)
            drawTable(newUser)
        })

})*/


