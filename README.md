# Foods-R-Us: An E-Commerce Website
![Demo](docs/demo.gif)

## Technological Requirements

* The project must be developed using **JEE** (servlets, jspx, tomcat, MVC, etc.)
* Any **JavaScript** you use must be your own. You **may not** use any library
  such as jQuery, Ext, or Dojo.
* You may use **images** or **CSS** developed by others with proper attribution.

## The Business Model

The Foods R Us Company is a consolidating retailer. It specializes in selling
food items but it does not stock inventory. Instead, it takes purchase orders
(P/O's) from its clients, consolidates them, and then procures them from
business partners who stock the items and provide them wholesale; i.e. the
partners do not venture into the retail market. In this business model, Foods R
Us makes money by benefiting from volume discounts (due to consolidation) and by
marking up wholesale prices. The latter factor, however, can backfire: if the
markup is too high, the Company will not be competitive, and if it is too low,
it becomes vulnerable to market fluctuations and this may lead to a net loss (by
under-selling the suppliers). The Company has to deal with this risk and attempt
to mitigate it, hence the need for sophisticated eCommerce technologies.

### Use Case #1: A client makes a fresh visit

The relevant URL is "host:port/eFoods". The Catalog servlet displays the catalog
of the Company. It is up to you to determine how this display is done based on
how much time you want to spend on this task and on how versatile you want your
webapp to be. For example, you can at one extreme display all the items in one
page; you can show the hierarchy by displaying only the categories and then the
selected items; you can enable both direct and category-based access; you can
have an express order form for those who know the item numbers; you can enable
clients to bookmark pages; etc. Whatever you do, the client must be able to see
the item's number, name, quantity per unit, and price. The client must also able
to add an item to an initially empty shopping cart.

### Use Case #2: A client adds an item to the shopping cart

The Cart servlet must react by displaying the content of the shopping cart. The
display should be tabular with one item per row. The table columns display the
number, name, and unit price of each item in a read-only fashion. In addition,
there should be a writable column for the desired quantity and a read-only one
for the extended price (defined as the quantity times the unit price). The
display should also indicate a total, a shipping cost ($5 that is waived for
orders of $100 or more before taxes), HST (13% of total, including shipping if
any), and a grand total. The page has three buttons: Update (to refresh the
calculated fields after editing a quantity), Continue Shopping, and Checkout.
Notice that if the entered quantity of an item is zero then it should be removed
from the cart. You need to also handle the case of negative or non-numeric
quantities.

### Use Case #3: A client logs in

Upon checkout (or optionally anytime before checkout at which the client chooses
to login), the request should be redirected to AUTH to determine and confirm the
client's identity. We assume that our clients have established accounts with the
AUTH server and, hence, their account numbers and account names can be retrieved
from AUTH upon a successful login.

### Use Case #4: A client checks out

Upon checkout, the controller must ensure the client is logged in and must then
display a confirmation screen followed by an acknowledgement that the the order
has been accepted and is being processed. A URL that the client can visit at any
time to view the created P/O must also be provided. See the next use case for
details.

### Use Case #5: A client visits the URL of a P/O

Upon confirmation of a P/O, the system stores its content in an XML file based
on the PO.xsd schema. The name of the P/O file is derived from the account
number of the client and the P/O number (a per-client serial number that starts
at 1). For example, the the 3rd P/O of account number 12345, is: po12345_03.xml.
Since this is an XML file, it needs to be transformed to XHTML before the client
can see it. Note that this use case does not involve authentication.

In addition to the above use cases, it is recommended that your site supports
the following uses: the ability to view the shopping cart from the catalog
screen (i.e. without having to add an item); the ability to checkout from the
catalog screen.

### Analytics and Special Features

Add support for the following:

* Management wants to be able to determine the average time it takes a client to
  add an item to the cart and the average time between a fresh visit and checkout
  (in the same session). Provide a mechanism by which these two averages can be
  viewed in real time.
* And on an ad-hoc basis, Management may decide to advertise a certain item
  whenever a related item is added to the cart. As a proof of concept, activate
  this by cross selling 2002H712 whenever 1409S413 is added to the cart. Keep in
  mind that this functionality should not be part of the webapp since Management
  may withdraw it at any time (w/o recompiling).
* Add a search facility to enable clients to look for items.
