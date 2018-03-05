function ajaxPart(typePara,urlPara,contentTypePara,dataTypePara,dataPara,successFunc){
    $.ajax({
        type:typePara,
        url:urlPara,
        contentType: contentTypePara,
        dataType:dataTypePara,
        data:dataPara,
        success:successFunc
    })
}
