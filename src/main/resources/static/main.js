let requestUrl = 'http://localhost:8080/admin/api/users'

//------ Table users ------------------------
function refreshData() {
    fetch(requestUrl)
        .then(response => response.json())
        .then(result => refreshTable(result))

    function refreshTable(users) {
        let tBody = ''
        $('#usersTable').find('tr').remove();
        $.each(users, function (key, object) {
            let roles = ''
            $.each(object.roles, function (k, o) {
                roles += o.name + ' '
            })
            tBody += ('<tr>');
            tBody += ('<td>' + object.id + '</td>');
            tBody += ('<td>' + object.username + '</td>');
            tBody += ('<td>' + object.firstName + '</td>');
            tBody += ('<td>' + object.lastName + '</td>');
            tBody += ('<td>' + object.email + '</td>');
            tBody += ('<td>' + roles.replaceAll('ROLE_', ' ') + '</td>');
            tBody += ('<td><button type="button" onclick="editModal(' + object.id + ')" ' +
                'class="btn btn-primary">Edit</button></td>');
            tBody += ('<td><button type="button" onclick="deleteModal(' + object.id + ')" ' +
                'class="btn btn-danger">Delete</button></td>');
            tBody += ('<tr>');
        });
        $('#usersTable').html(tBody);
    }
}

//------ Create new user ------------------------

function addNewUser() {
    fetch(requestUrl, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',

        body: JSON.stringify({
            username: $('#newUsername').val(),
            firstName: $('#newFirstName').val(),
            lastName: $('#newLastName').val(),
            password: $('#newPassword').val(),
            email: $('#newEmail').val(),
            roles: [
                document.getElementById('newRoles').value
            ]

        })
    })
        .then((r) => {
            if (r.ok) {
                $('form input[type="text"], form input[type="password"], form input[type="number"], form textarea')
                    .val('');
                $('#nav-home-tab').tab('show')
                refreshData()
            }
        })

}


//------ Modal update user ------------------------
function editModal(id) {
    fetch(requestUrl + '/' + id)
        .then(response => response.json())
        .then(result => fillFields(result))
    function fillFields(user) {
        $('#edId').val(user.id);
        $('#edUsername').val(user.username);
        $('#edPassword').val(user.password);
        $('#edFirstName').val(user.firstName);
        $('#edLastName').val(user.lastName);
        $('#edEmail').val(user.email);
        $('#editModal').modal()
        $('#edit').attr('onclick','editUser(' + user.id + ')')
    }
}

function editUser(id) {
    fetch(requestUrl + '/' + id,
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            body: JSON.stringify(
                {
                    id: document.getElementById('edId').value,
                    name: document.getElementById('edFirstName').value,
                    lastName: document.getElementById('edLastName').value,
                    email: document.getElementById('edEmail').value,
                    username: document.getElementById("edUsername").value,
                    password: document.getElementById('edPassword').value,
                    roles: [
                        document.getElementById('rolesEdit').value
                    ]
                })
        }).then((re) => {
        $('#editModal').modal("hide")
        refreshData()
    })
}

//------ Modal delete user ------------------------
function deleteModal(id) {
    fetch(requestUrl + '/' + id)
        .then(response => response.json())
        .then(result => fillFields(result))

    function fillFields(user) {
        $('#delId').val(user.id);
        $('#delUserName').val(user.username);
        $('#delFirstName').val(user.firstName);
        $('#delLastName').val(user.lastName);
        $('#delEmail').val(user.email);
        $('#delete').attr('onclick', 'deleteUser(' + user.id + ')')
        $('#deleteModalHtml').modal()
    }
}

function deleteUser(id) {
    fetch(requestUrl + '/' + id, {
        method: 'DELETE'
    }).then(() => {
        $('#deleteModalHtml').modal('hide')
        refreshData();
    })
}


refreshData()