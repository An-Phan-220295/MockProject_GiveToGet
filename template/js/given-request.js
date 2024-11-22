$(document).ready(function(){
    var contentElement = $("#list-request")
    var paginElement = $("#pagination")
    $.ajax({
        url: "http://localhost:8080/api/v1/givenrequest",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(data) {
            if(data.code == "01"){
                var html ="<h5 class='text-center'>No data</h5>"
                contentElement.append(html);
                paginElement.addClass("invisible")
            }
            else if(data.code == "00"){
                var request = data.data
                request.forEach(element => {
                    var html = `<div class="mb-3">
            <a href="" style="text-decoration: none">
              <div class="card">
                <div class="card-body row">
                  <div
                    class="col-2 d-flex justify-content-center align-items-center"
                  >
                    <img
                      src="/image/${element.image}"
                      class="img-thumbnail mx-auto"
                      alt="..."
                      style="width: 120px"
                    />
                  </div>
                  <div class="col-10">
                    <div class="card-title mb-3">
                      <div class="d-flex justify-content-between">
                        <h5 class="">${element.title}</h5>
                        <p class="mb-0">${element.createDate}</p>
                      </div>
                      <p class="mb-0">${element.userName}</p>
                      <p class="mb-0">
                        Address: ${element.address}
                      </p>
                      <p>Request items: ${element.itemsName.join(", ")}</p>
                    </div>
                    <p class="card-text">
                    ${element.content}
                    </p>
                  </div>
                </div>
              </div>
            </a>
          </div>`
                    contentElement.append(html);
                });
                
            }
        }
    });
})