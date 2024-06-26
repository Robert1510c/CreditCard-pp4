const getProducts = () => {
  return fetch("/api/products")
  .then(response => response.json());
}


const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response => response.json());
}

const addProduct = (productId) => {
    return fetch(`/api/add-product/${productId}`, {
        method: "POST"
    });
}

const acceptOffer = (acceptOfferRequest) => {
    return fetch("/api/accept-offer", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(acceptOfferRequest)
    })
        .then(response => response.json());
}


const checkoutForm = document.querySelector('#checkout');
checkoutForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const acceptOfferRequest = {
        firstname: checkoutForm.querySelector('input[name="firstname"]').value,
        lastname: checkoutForm.querySelector('input[name="lastname"]').value,
        email: checkoutForm.querySelector('input[name="email"]').value
    }
    acceptOffer(acceptOfferRequest)
        .then(reservationDetails => {
            console.log(reservationDetails)
            window.location.href = reservationDetails.paymentUrl;
        });
})


const createProductHtml = (productData) => {

    const template = `
        <div>
            <img src="https://picsum.photos/id/237/200/300"/>
            <h4>${productData.name}</h4>
            <span>${productData.description}</span>
            <span>${productData.price}</span>
            <button data-id="${productData.id}">Add to cart</button>
        </div>
    `;

    const productEl = document.createElement('li');
    productEl.innerHTML = template.trim();

    return productEl;
}

const refreshOffer = () => {
    // console.log("let refresh current offer");

    const totalEl = document.querySelector('#offer__total');
    const itemsEl = document.querySelector('#offer__items-count');

    getCurrentOffer()
        .then(offer => {
            totalEl.textContent = `${offer.total} PLN`;
            itemsEl.textContent = `${offer.itemsCount} Items`;
        });
}




const initializeCartHandler = (productHtmlEl) => {
    const addToCartBtn = productHtmlEl.querySelector("button[data-id]");
    addToCartBtn.addEventListener("click", (event) => {
        const productId = event.target.getAttribute("data-id");

        addProduct(productId)
            .then(refreshOffer());
    });

    return productHtmlEl;
}


document.addEventListener("DOMContentLoaded", () => {
    const productsListEl = document.querySelector("#productsList");
    getProducts()
        .then(productsAsJsonObj => productsAsJsonObj.map(createProductHtml))
        .then(productsAsHtmlEl => productsAsHtmlEl.map(initializeCartHandler))
        .then(productsAsHtmlEl => {
            productsAsHtmlEl.forEach(productEl => productsListEl.appendChild(productEl));
        })
    refreshOffer();
});
