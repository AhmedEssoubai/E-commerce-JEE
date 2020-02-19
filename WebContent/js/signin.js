function utilisateurChanger(id){
    let autreId;
    if (id == 'client')
    {
        document.querySelector('#email_part').value = '.c@isil.este';
        autreId = '#lb_fournisseur';
    }
    else
    {
        document.querySelector('#email_part').value = '.f@isil.este';
        autreId = '#lb_client';
    }
    document.querySelector('#lb_' + id).style.color = '#3498db';
    document.querySelector(autreId).style.color = 'rgb(66, 70, 73, 0.5)';
}


utilisateurChanger(document.querySelector("#client").checked ? "client" : "fournisseur");