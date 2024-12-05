$(document).ready(function () {
    // Add new item row
    $("#add-item-btn").on("click", function () {
        const newItemRow = `
            <div class="mb-3 row gx-2 align-items-center">
                <div class="col-md-3">
                    <input
                        type="text"
                        class="form-control item-name"
                        placeholder="Name"
                    />
                </div>
                <div class="col-md-2">
                    <input
                        type="number"
                        class="form-control item-quantity"
                        min="1"
                        placeholder="Quantity"
                    />
                </div>
                <div class="col-md-3 ms-auto text-end">
                    <button type="button" class="btn btn-danger btn-sm remove-item-btn">
                        Remove
                    </button>
                </div>
            </div>`;
        $("#items-container").append(newItemRow);
    });

    // Remove an item row
    $(document).on("click", ".remove-item-btn", function () {
        $(this).closest(".row").remove();
    });

    $(document).ready(function () {
        $("#create-request-form").on("submit", function (e) {
            e.preventDefault();

            let isValid = true;
            let errorMessage = "Please fill all mandatory fields";

            const title = $("#title");
            const description = $("#description");
            const imageUploads = $("#image-uploads")[0].files;

            validateField(title);
            validateField(description);

            if (!imageUploads.length) {
                isValid = false;
                setFieldError($("#image-uploads"));
            } else {
                setFieldValid($("#image-uploads"));
            }

            let items = [];
            $(".item-name").each(function (index) {
                const name = $(this).val().trim();
                const quantity = $(".item-quantity").eq(index).val().trim();

                if (!name || !quantity || quantity <= 0) {
                    isValid = false;
                    setFieldError($(this));
                    setFieldError($(".item-quantity").eq(index));
                } else {
                    setFieldValid($(this));
                    setFieldValid($(".item-quantity").eq(index));
                    items.push({ itemName: name, quantities: parseInt(quantity, 10) });
                }
            });

            if (!isValid) {
                $("#error").text(errorMessage);
                return;
            }

            const data = {
                title: title.val().trim(),
                description: description.val().trim(),
                item: items,
            };

            const formData = new FormData();
            formData.append("data", new Blob([JSON.stringify(data)], { type: "application/json" }));
            for (let i = 0; i < imageUploads.length; i++) {
                formData.append("files", imageUploads[i]);
            }

            $.ajax({
                url: "http://localhost:8080/api/v1/givenrequest/create",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    alert("Request submitted successfully!");
                    $("#create-request-form")[0].reset();
                    $("#items-container").empty();
                },
                error: function (xhr) {
                    alert(`Failed to submit the request: ${xhr.responseText}`);
                },
            });

            function validateField(field) {
                if (!field.val().trim()) setFieldError(field);
                else setFieldValid(field);
            }

            function setFieldError(field) {
                field.css("border-color", "red");
            }

            function setFieldValid(field) {
                field.css("border-color", "");
            }
        });
    });

});
