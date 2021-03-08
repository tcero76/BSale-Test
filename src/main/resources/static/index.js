window.addEventListener('load', function(e) {
var container = document.getElementById("idCatalogo");
var searchInput = document.getElementById("id__search__input");
var searchButton = document.getElementById("id__search__button")
searchButton.addEventListener('click', e => {
    e.preventDefault();
    fetchProducts(searchInput.value)
 });
searchInput.addEventListener('keydown', e => {
    if(e.key=='Enter') {
        e.preventDefault();
        fetchProducts(searchInput.value)
    }
});
fetchProducts("");

function card(d) {
    return `<div class="card"><div class="card__image" style="background-image:url('${d.url_image}')"></div><div class="card__name"><label class="card__product-name">${d.name}</label></div><div class="card__detail">$ ${d.price.toFixed(0)}</div></div>`;
}

function fetchProducts(input) {
    if(input=="") {
        fetch(`/products`)
            .then(res => res.json())
            .then(data => {
                container.innerHTML = '';
                data
                .sort((d1,d2) => d1<d2)
                .forEach(d => {
                    container.innerHTML += card(d);
                });
            })
            .catch(err => console.log(err))
    } else {
        fetch(`/${input.toUpperCase()}/products/`)
            .then(res => res.json())
            .then(data => {
                container.innerHTML = '';
                data
                .sort((d1,d2) => d1<d2)
                .forEach(d => {
                    container.innerHTML += card(d);
                });
            })
            .catch(err => console.log(err))
    }
   }
})