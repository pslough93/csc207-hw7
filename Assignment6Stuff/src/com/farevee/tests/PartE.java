package com.farevee.tests;

public class PartE {
	
	/**
	 * Part E - Reflections
	 * 
	 * BulkContainer and BulkItem are related in much the same way as Package and
	 * ManyPackages, but are implemented differently. BulkContainer is a class that
	 * extends BulkItem, but ManyPackages has a package as a field, along with an
	 * integer quantity field. Ultimately I think that the BulkContainer/BulkItem 
	 * way of doing things is far more elegant because it allows calls to super() 
	 * methods. While the other way forces you to make sure the parameters of the 
	 * package match up in various methods, ultimately the BulkContainer way of doing
	 * it is easier and probably safer because the methods exclusively contained in
	 * BulkItem are encapsulated from changes in BulkContainer.
	 * 
	 * My remove and merge methods rely on a getName() (implemented in the item 
	 * interface) and an equals method respectively. For the remove method, the getName
	 * (or if a hasName method was implemented instead) method seems to be the most
	 * complete way of doing the remove, because it will get items of a certain across
	 * all subclasses. I don't think it would help to have these methods access the item
	 * name directly, as that would require name to be totally mutable by any method, which
	 * could lead to problems. I think the merge method works smoother with the equals method
	 * because in this case, most pieces of the items information have to match up to
	 * be mergable. All of my equals methods check that, so it is worth using the equals
	 * methods here. 
	 * 
	 * In the sense that this piece of software is for a grocery store, it makes sense to
	 * have this distinction even though the items have the same fields. Another approach
	 * would be to have an isFood() boolean method in the item interface, but that seems 
	 * a bit much when it would make sense for a grocery store to make distinctions like
	 * this. This code is also more maintainable because NonFood does not have anything to
	 * do with a MultiplePackages class or merge() method. This makes it more manageable
	 * because there is only so much that could go wrong with the NonFood class then
	 * 
	 * There are several differences between Rebelsky's and Walkers grocery store 
	 * implementations. Here are several:
	 * 1) Walker has an Item as a Class rather than an Interface.
	 * 2) Walker has a subclass for Beverages
	 * 3) Walker has combined all Bulk things into one thing: Produce
	 * 4) Walker uses doubles for prices
	 * 5) Walker has no NonFood class. 
	 * I think points 2 and 3 make the most sense to implement. Having beverages as a 
	 * subclass avoids the awkward "12 oz Package of Coke" that we see with the toString()
	 * method currently. Also, the bulk classes are a bit unwieldy, so the Produce class would
	 * be a welcome change. Point 5 is debatable, as we discussed above that non-Food items 
	 * could be rolled in with Packages. I dont like points 1 and 4. Items should be an interface
	 * because the toString and getWeight methods should be implemented differently for each
	 * type of food. Also, there is no need for the precision granted by using doubles for 
	 * prices and such.
	 */
	
}
