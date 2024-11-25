$(document).ready(function () {
  var contentElement = $("#list-request");
  var paginElement = $("#pagination");
  var currentPage = 0;

  function loadPage(page) {
      contentElement.empty(); // Clear current content
      $.ajax({
          url: "http://localhost:8080/api/v1/givenrequest",
          type: 'GET',
          dataType: 'json',
          data: { pageNumber: page },
          success: function (data) {
              if (data.code === "01") {
                  var html = "<h5 class='text-center'>No data</h5>";
                  contentElement.append(html);
                  paginElement.addClass("invisible");
              } else if (data.code === "00") {
                  var request = data.data.data;
                  request.forEach(element => {
                      var html = `<div class="mb-3">
                          <a href="" style="text-decoration: none" request-id="${element.id}">
                              <div class="card">
                                  <div class="card-body row">
                                      <div class="col-2 d-flex justify-content-center align-items-center">
                                          <img src="/image/${element.image}" class="img-thumbnail mx-auto" alt="..." style="width: 120px" />
                                      </div>
                                      <div class="col-10">
                                          <div class="card-title mb-3">
                                              <div class="d-flex justify-content-between">
                                                  <h5>${element.title}</h5>
                                                  <p class="mb-0">${element.createDate}</p>
                                              </div>
                                              <p class="mb-0">${element.userName}</p>
                                              <p class="mb-0">Address: ${element.address}</p>
                                              <p>Request items: ${element.itemsName}</p>
                                          </div>
                                          <p class="card-text">${element.content}</p>
                                      </div>
                                  </div>
                              </div>
                          </a>
                      </div>`;
                      contentElement.append(html);
                  });

                  // Update pagination
                  paginElement.empty();
                  var totalPage = data.data.totalPage;
                  if (totalPage > 1) {
                      var html = `<li class="page-item ${page === 0 ? "disabled" : ""}">
                          <a class="page-link" href="#" aria-label="Previous" data-page="${page}">
                              <span aria-hidden="true">&laquo;</span>
                          </a>
                      </li>`;
                      for (var i = 0; i < totalPage; i++) {
                          html += `<li class="page-item ${page === i ? "active" : ""}">
                              <a class="page-link" href="#" data-page="${i}">${i+1}</a>
                          </li>`;
                      }
                      html += `<li class="page-item ${page === totalPage ? "disabled" : ""}">
                          <a class="page-link" href="#" aria-label="Next" data-page="${page+1}">
                              <span aria-hidden="true">&raquo;</span>
                          </a>
                      </li>`;
                      paginElement.append(html);
                      paginElement.removeClass("invisible");
                  }
              }
          }
      });
  }

  // Handle pagination click
  $(document).on("click", "#pagination a", function (e) {
      e.preventDefault();
      var page = parseInt($(this).data("page"));
      if (!isNaN(page)) {
        console.log(page)
          currentPage = page;
          loadPage(page);
      }
  });

  // Initial load
  loadPage(currentPage);
});
