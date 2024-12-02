$(document).ready(function () {
$("#add-item-btn").on("click", function () {
    const newItemRow = `
          <div class="row mb-2 align-items-center">
            <div class="col-md-6">
              <select class="form-select item-select" required>
                <option value="">Choose an item</option>
                <option value="item1">Item 1</option>
                <option value="item2">Item 2</option>
                <option value="item3">Item 3</option>
              </select>
            </div>
            <div class="col-md-4">
              <input
                type="number"
                class="form-control item-quantity"
                min="1"
                placeholder="Quantity"
                required
              />
            </div>
            <div class="col-md-2 text-end">
              <button type="button" class="btn btn-danger btn-sm remove-item-btn">Remove</button>
            </div>
          </div>
        `;
    $("#items-container").append(newItemRow);
});

// Remove an item row
$(document).on("click", ".remove-item-btn", function () {
    $(this).closest(".row").remove();
});
});
