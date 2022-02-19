if (document.readyState === 'loading') document.addEventListener('DOMContentLoaded', ready)    ;  //make sure page is loaded
else ready();

function ready()        //add all eventListeners to all necessary buttons when page finishes loading                                        
{
    var addButtons = document.getElementsByClassName('add');
    for(var i = 0; i < addButtons.length; i++)
    {
        var button = addButtons[i];
        button.addEventListener('click', addToCartClicked);
    }

    document.getElementsByClassName('checkout')[0].addEventListener('click', checkoutClicked);
}

function openCart()     //opens the cart sidebar
{
    document.getElementById("cart").style.width = "30%";
    document.getElementById("grid").style.marginRight = "calc(2em + 30%)";
    document.getElementById("menu").style.marginRight = "30%";
    document.getElementById("icon").style.display = "none";
    
    var x = document.getElementsByClassName("cart-item");
    
    for(var i = 0; i < x.length; i++)
    {
       x[i].style.display = "flex";
    }
}

function closeCart()    //closes the cart sidebar
{
    document.getElementById("cart").style.width = "0";
    document.getElementById("grid").style.marginRight = "2em";
    document.getElementById("menu").style.marginRight = "0";
    document.getElementById("icon").style.display = "initial";
    
    var x = document.getElementsByClassName("cart-item");
    
    for(var i = 0; i < x.length; i++)
    {
       x[i].style.display = "initial";
    }
}

function addToCartClicked(event)    //grabs the corresponding item name and price when Add To Cart is clicked and adds it to cart
{ 
    var buttonClicked = event.target;
    var item = buttonClicked.parentElement;
    var itemName = item.getElementsByClassName('shop-item')[0].innerText;
    var itemPrice = item.getElementsByClassName('shop-price')[0].innerText;
    addToCartItem(itemName, itemPrice);
    updateCartTotal();
}

function addToCartItem(name, price)     //the functions that adds the item to the cart sidebar
{
    var cartItem = document.createElement('div');
    cartItem.classList.add('cart-item');
    var cartCart = document.getElementsByClassName('cart-actual')[0];
    var itemNames = document.getElementsByClassName('item-name');
    for(var i = 0; i < itemNames.length; i++)
    {
        if(itemNames[i].innerText === name)
        {
            alert('This item is already in the cart');
            return;
        }
    }
    var cartItemDeets =
        `
        <div class="item-name cart-column">
            <span>${name}</span>
        </div>
        <span class="item-price cart-column">${price}</span>
        <div class="item-qty cart-column">
            <input class="qty" type="number" value="1">
            <button class="remove" type="button">Remove</button>
        </div>
        `;
    cartItem.innerHTML = cartItemDeets;
    cartCart.append(cartItem);
    cartItem.getElementsByClassName('remove')[0].addEventListener('click', removeFromCartClicked);  //adds eventListeners to quantity and remove
    cartItem.getElementsByClassName('qty')[0].addEventListener('change', qtyChanged);               //buttons to the item added to cart
    
    var x = document.getElementsByClassName("cart-item");
    if(document.getElementById("icon").style.display === "none")
    {
        x[x.length-1].style.display = "flex";
    }
    else
    {
        x[x.length-1].style.display = "initial";
    }
}

function qtyChanged(event)      //prevents quantity from being less than 1
{
    var input = event.target;
    if (isNaN(input.value) || input.value <= 0) 
    {
        input.value = 1;
        alert('You cannot buy zero or less items, you subhuman sack of shit.');
    }
    updateCartTotal();
}

function removeFromCartClicked(event)   //removes item from cart sidebar
{
    var buttonClicked = event.target;
    buttonClicked.parentElement.parentElement.remove();
    updateCartTotal();
}

function checkoutClicked()      //removes all items from cart
{
    var cartStuff = document.getElementsByClassName('cart-actual')[0];
    if(!cartStuff.hasChildNodes())
    {
        alert('There are no items in your cart.');
        return;
    }
    while(cartStuff.hasChildNodes()) cartStuff.removeChild(cartStuff.firstChild);
    alert('Going to Checkout');
    updateCartTotal();
}

function updateCartTotal()      //updates the total price at the bottom of the cart
{
    var cartActual = document.getElementsByClassName('cart-actual')[0];
    var cartItems = cartActual.getElementsByClassName('cart-item');
    var total = 0;
    for(var i = 0; i < cartItems.length; i++)
    {
        var cartItem = cartItems[i];
        var itemPrice = cartItem.getElementsByClassName('item-price')[0];
        var itemQty = cartItem.getElementsByClassName('qty')[0];
        var price = parseFloat(itemPrice.innerText.replace('$', ''));
        var qty = itemQty.value;
        total = total + (price * qty);                                          //Total
    }
    document.getElementsByClassName('total-price')[0].innerText = '$' + total.toFixed(2);
}