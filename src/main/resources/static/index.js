var state = {categories: [], products: []}
var container;
const nItems = 3;

window.addEventListener('load', function(e) {
    container = document.getElementById("idcontainer");
    var searchButton = document.getElementById("id__search__button");
    var searchInput = document.getElementById("id__search__input");
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
});

function card(d) {
    return `<div class="card"><div class="card__image" style="background-image:url('${d.url_image}')"></div><div class="card__name"><label class="card__product-name">${d.name}</label></div><div class="card__detail">$ ${d.price.toFixed(0)}<span class="card__icon"><svg class="card__add__icon"></svg></span></div></div>`;
}

function fetchProducts(input) {
    if(input=="") {
        fetch(`/products`)
            .then(res => res.json())
            .then(data => renderCatalogo(data))
            .catch(err => console.log(err))
    } else {
        fetch(`/${input.toUpperCase()}/products/`)
            .then(res => res.json())
            .then(data => renderCatalogo(data))
            .catch(err => console.log(err))
    }
   }
function renderCatalogo(data) {
    container.innerHTML = '';
    state.categories = Array.from(new Set(data.map(d=>d.category)))
        .sort((c1,c2) => {
            if(c1<c2) return 1;
            if(c2>c2) return -1;
            return 0;
        })
        .map(c=>({ name:c,
                   id: data.filter(d=>d.category==c)[0].idcategory,
                   totalItems: data.filter(d=>d.category==c).length,
                   page:1,
                   npage: ~~(data.filter(d=>d.category==c).length/3)
                 }))
    state.products = data;
    renderCategoria();
}
function renderCategoria() {
    state.categories
       .forEach(c=> {
           container.innerHTML += `<H2 class="container__category">${c.name.toUpperCase()}</H2>`
           container.innerHTML += `<div id="category${c.id}" class="catalogo"></div>`
           renderCategoryItem(c);
       })
}
function renderCategoryItem(c) {
   var category = document.getElementById(`category${c.id}`);
   category.innerHTML = renderIconLeft(c);
   state.products
       .filter(d => d.category == c.name)
       .sort((d1,d2) => {
       if(d1.name<d2.name) {
           return -1;
       } else if(d1.name>d2.name) {
           return 1;
       } else {
           return 0;
       }
       })
       .slice((c.page-1)*nItems,c.page*nItems>=c.totalItems?c.totalItems:c.page*nItems)
       .forEach(d => {
           category.innerHTML += card(d);
       });
   category.innerHTML += renderIconRight(c);
}

function renderIconLeft(c) {
    var opacity = (c.page>1)?"1":"0";
    return `<div id="previousPage${c.id}" style="opacity:${opacity} " onclick="onClickPrevious(event, ${c.id})" class="previousPage__icon-box${(c.page>=c.npage)?' disable':''}"></div>`
}

function renderIconRight(c) {
    var opacity = (c.page<=c.npage && c.totalItems>c.page*nItems)?"1":"0";
    return `<div id="nextPage${c.id}" style="opacity:${opacity}" onclick="onClickNext(event, ${c.id})" class="nextPage__icon-box${(c.page>=c.npage)?' disable':''}"></div>`
}

function onClickPrevious(e,id) {
    let c = state.categories.find(c=>c.id==id)
    var previous = document.getElementById(`previousPage${c.id}`)
    if(c.page>1) c.page--
    renderCategoryItem(c);
}

function onClickNext(e,id){
    let c = state.categories.find(c=>c.id==id)
    var next = document.getElementById(`nextPage${c.id}`)
    if(c.page<=c.npage && c.totalItems>c.page*nItems) c.page++
    renderCategoryItem(c);
}