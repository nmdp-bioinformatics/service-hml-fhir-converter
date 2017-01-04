/**
 * Created by abrown3 on 12/27/16.
 */

(function () {
    var dbName = 'nmdp_interop',
        client = new Mongo(),
        db = client.getDB(dbName);

    var collections = [
        {
            name: 'Hml.TypingTestNames',
            defaultData: [
                { name: 'Illumina 454', description: 'Illumina 454 sequencing', active: true, dateCreated: new Date() },
                { name: 'RNA Seq', description: 'RNA Seq sequencing', active: true, dateCreated: new Date()},
                { name: 'Ion Torrent', description: 'Ion Torrent sequencing', active: true, dateCreated: new Date() }
            ]
        }
    ];

    for (var i = 0; i < collections.length; i++) {
        var collection = db.getCollection(collections[i].name);

        db.createCollection(collections[i].name);
        collection.insert(collections[i].defaultData);
    }
}());