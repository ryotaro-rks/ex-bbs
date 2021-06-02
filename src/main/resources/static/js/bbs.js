$(function(){
    $('.goodNumbers').text(0);
    $('.goodNumbers').val(0);

    // button自身のvalueを取得するというような方針で作る
    $('.goodIcon').on('click', function () {
    	let clickedArticleId = $(this).attr("id");
    	console.log(clickedArticleId);
        let goodNumbers = $("#goodNumbers" + clickedArticleId).val();
        console.log(goodNumbers);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/bbs/pushGood",
            data: {
                goodNumbers: goodNumbers
            },
            dataType: "json",
            async: true
        }).done(function(data){
            console.log(data);
            $("#goodNumbers" + clickedArticleId).text(data.goodNumbers);
            $("#goodNumbers" + clickedArticleId).val(data.goodNumbers);
        }).fail(function (XMLHttpRequest, textStatus, errorThrown){
            alert('正しい結果を得られませんでした');
            console.log("XMLHttpRequest", XMLHttpRequest.status);
            console.log("textStatus", textStatus.status);
            console.log("errorThrown", errorThrown.status);
        });
    });
});