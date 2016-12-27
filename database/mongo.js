/**
 * Created by abrown3 on 12/27/16.
 */
(function () {
    var connection = new Mongo(),
        typingTestNames = connection.getDB('TypingTestNames');

    typingTestNames.TypingTestNames.insert([
        { name: 'Illumina 454', description: 'Illumina 454 sequencing' },
        { name: 'RNA Seq', description: 'RNA Seq sequencing' },
        { name: 'Ion Torrent', description: 'Ion Torrent sequencing' }
    ]);
}());