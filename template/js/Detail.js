$(document).ready(function () {
  // Xử lý khi nhấn nút Accept Request
  $("#accept-request-btn").on("click", function (e) {
    e.preventDefault(); // Ngăn hành động mặc định của thẻ <a>

    // Kiểm tra trạng thái hiện tại
    const isAccepted = $(this).hasClass("accepted");

    if (!isAccepted) {
      // Nếu chưa được Accept, hiển thị khung chat và đổi sang Cancel
      $("#chat-section").show(); // Hiển thị khung chat
      $("#open-modal-btn").show();
      $(this).text("Cancel Request"); // Thay đổi nội dung nút
      $(this).removeClass("btn-primary").addClass("btn-danger"); // Đổi màu nút
      $(this).addClass("accepted"); // Đánh dấu trạng thái
    } else {
      // Nếu đã Accept, ẩn khung chat và đổi lại thành Accept
      $("#chat-section").hide(); // Ẩn khung chat
      $("#open-modal-btn").hide();
      $(this).text("Accept Request"); // Đổi lại nội dung nút
      $(this).removeClass("btn-danger").addClass("btn-primary"); // Đổi lại màu nút
      $(this).removeClass("accepted"); // Xóa trạng thái
    }
  });

  $("#open-modal-btn").on("click", function (e) {
    e.preventDefault();
    $("#item-modal").fadeIn(); // Hiển thị modal
  });

  // Đóng modal
  $("#close-modal, #cancel-btn").on("click", function () {
    $("#item-modal").fadeOut(); // Ẩn modal
  });

  // Quản lý số lượng
  const maxQuantity = 10; // Số lượng tối đa
  let currentQuantity = 0;

  $("#increase-qty").on("click", function () {
    if (currentQuantity < maxQuantity) {
      currentQuantity++;
      $("#item-quantity").text(currentQuantity);
    }
  });

  $("#decrease-qty").on("click", function () {
    if (currentQuantity > 0) {
      currentQuantity--;
      $("#item-quantity").text(currentQuantity);
    }
  });

  // Xác nhận
  $("#confirm-btn").on("click", function () {
    const itemName = $("#item-name").text();
    alert(`Confirmed ${itemName} with quantity: ${currentQuantity}`);
    $("#item-modal").fadeOut(); // Ẩn modal sau khi xác nhận
  });

  const urlParams = new URLSearchParams(window.location.search);
  const id = urlParams.get("id");
  var detail = $("#detail-content");
  loadDetail(id, detail);
});

function loadDetail(requestId, detailElement) {
  $.ajax({
    url: `http://localhost:8080/api/v1/givenrequest/detail?id=${requestId}`,
    type: "GET",
    dataType: "json",
    success: function (data) {
      if (data.code === "00") {
        const request = data.data;

        let imageIndicators = "";
        let imageItems = "";
        request.images.forEach((image, index) => {
          imageIndicators += `
                <button
                  type="button"
                  data-bs-target="#carouselExampleIndicators"
                  data-bs-slide-to="${index}"
                  class="${index === 0 ? "active" : ""}"
                  aria-current="${index === 0 ? "true" : "false"}"
                  aria-label="Slide ${index + 1}"
                ></button>`;
          imageItems += `
                <div class="carousel-item ${index === 0 ? "active" : ""}">
                  <img
                    src="../image/${image.imageName}" 
                    class="d-block w-100"
                    alt="${image.imageName}"
                    style="height: 300px; object-fit: cover"
                  />
                </div>`;
        });

        // Tạo HTML cho chi tiết yêu cầu
        const html = `
              <div class="row">
                <!-- Carousel -->
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                  <div id="carouselExampleIndicators" class="carousel slide w-100" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                      ${imageIndicators}
                    </div>
                    <div class="carousel-inner">
                      ${imageItems}
                    </div>
                    <button
                      class="carousel-control-prev"
                      type="button"
                      data-bs-target="#carouselExampleIndicators"
                      data-bs-slide="prev"
                    >
                      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                      <span class="visually-hidden">Previous</span>
                    </button>
                    <button
                      class="carousel-control-next"
                      type="button"
                      data-bs-target="#carouselExampleIndicators"
                      data-bs-slide="next"
                    >
                      <span class="carousel-control-next-icon" aria-hidden="true"></span>
                      <span class="visually-hidden">Next</span>
                    </button>
                  </div>
                </div>
      
                <!-- Thông tin chi tiết -->
                <div class="col-md-6 offset-md-1">
                  <div class="card border-0">
                    <div class="card-body">
                      <div class="mb-2">
                        <h5>${request.title}</h5>
                        <span class="text-muted" style="font-size: 0.9rem; float: right">
                          Date Create: ${request.createDate}
                        </span>
                      </div>
                      <div class="card-text">
                        <strong>Description:</strong> ${
                          request.description
                        }<br />
                        <strong>Requested by:</strong> ${request.userName}<br />
                        <strong>Address:</strong> ${request.address}<br />
                        <strong>Items:</strong><br />
                        <ul>
                          ${Object.entries(request.items)
                            .map(
                              ([itemName, quantity]) =>
                                `<li>${itemName} - Quantity: ${quantity}</li>`
                            )
                            .join("")}
                        </ul>
                      </div>
                      <a href="#" class="btn btn-primary col-md-12 mb-2" id="accept-request-btn">Accept Request</a>
                    </div>
                  </div>
                </div>
              </div>`;

        // Thêm nội dung vào container
        detailElement.html(html);
      } else if (data.code !== "00") {
        detailElement.html("<h5 class='text-center'>No data</h5>");
      }
    },
    error: function (error) {
      console.error("Error fetching request details:", error);
    },
  });
}
