(function() {
	"use strict";
	angular.module('common').service('MenuService', MenuService);
	MenuService.$inject = [ '$http', 'ApiPath', 'ApiMVC', '$q' ];

	function MenuService($http, ApiPath, ApiMVC, $q) {
		var service = this;
		service.getCategories = function() {
			$http.get('http://localhost:8001/ZulfiCricket/home', {
				headers : {
					'Access-Control-Allow-Origin' : '*',
					'Content-Type' : 'application/json'
				}
			}).then(function(response) {
				return response.data;
			});
		};
		service.getMenuItems = function(category) {
			var config = {};
			if (category) {
				config.params = {
					'category' : category
				};
			}
			return $http.get(ApiPath + '/menu_items.json', config).then(function(response) {
				return response.data;
			});
		};

		// To get the Team players from Registered roster
		service.getTeamPlayers = function() {
			var deferred = $q.defer();
			$http.get(ApiMVC + '/players/selection', {
				headers : {
					'Access-Control-Allow-Origin' : '*',
					'Content-Type' : 'application/json'
				}
			}).then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				console.error('Error while getting players list for match selection');
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}

		// To submit player availability
		service.playerForSelection = function(player, availability) {
			var deferred = $q.defer();

			var playerAvailability = {
				player_id : player.player_id,
				player_firstName : player.player_firstName,
				player_lastName : player.player_lastName,
				player_availability : availability
			};

			$http.post(ApiMVC + '/submit/availability', playerAvailability, {
				headers : {
					'Access-Control-Allow-Origin' : '*',
					'Content-Type' : 'application/json'
				},
			}).then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				console.error('in Menu Service: Error while submitting player availability');
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}

		// Submitting and retrieving selected player for Playing XI
		service.submittingPlayingXI = function(player) {
			var deferred = $q.defer();
			var paramData = JSON.stringify(player);
			$http.post(ApiMVC + '/submit/playingXI', paramData, {
				headers : {
					'Access-Control-Allow-Origin' : '*',
					'Content-Type' : 'application/json'
				}
			}).then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				console.error('In Menu Service: Error while submitting Playing XI list');
				deferred.reject(errResponse);
			});
			return deferred.promise;

		};

		service.getMatchesResult = function() {
			// var config = {};
			// var gUrl =
			// '/Module-5-Assignment-master/src/common/model/demo.text',
			// lUrl = '/src/common/model/demo.text';
			// return $http.get(gUrl, config).then(function (response) {
			// return response.data;
			// });
			var data = [ {
				"id" : 1,
				"match_date" : "11/17/2016",
				"host-team" : "Chatterpoint",
				"guest-team" : "arcu libero rutrum",
				"ground" : "ante vestibulum ante",
				"man-of-the-match" : "Jesse Burke",
				"status" : "pede venenatis non sodales sed tincidunt"
			}, {
				"id" : 2,
				"match_date" : "2/6/2017",
				"host-team" : "Oyonder",
				"guest-team" : "malesuada in imperdiet et",
				"ground" : "fringilla rhoncus mauris",
				"man-of-the-match" : "Emily Wallace",
				"status" : "nam congue risus semper porta volutpat quam pede"
			}, {
				"id" : 3,
				"match_date" : "12/17/2016",
				"host-team" : "Meetz",
				"guest-team" : "pellentesque volutpat dui",
				"ground" : "vivamus in felis",
				"man-of-the-match" : "Stephanie Holmes",
				"status" : "ac enim in tempor turpis nec"
			}, {
				"id" : 4,
				"match_date" : "11/25/2016",
				"host-team" : "Voonix",
				"guest-team" : "quam a odio in",
				"ground" : "ante ipsum primis in",
				"man-of-the-match" : "Ralph Wilson",
				"status" : "ultrices libero non mattis pulvinar nulla pede"
			}, {
				"id" : 5,
				"match_date" : "12/6/2016",
				"host-team" : "Thoughtbeat",
				"guest-team" : "amet nulla quisque",
				"ground" : "nulla elit ac",
				"man-of-the-match" : "Wayne Hanson",
				"status" : "eleifend pede libero quis orci"
			}, {
				"id" : 6,
				"match_date" : "10/16/2016",
				"host-team" : "Wikizz",
				"guest-team" : "condimentum curabitur in",
				"ground" : "est lacinia nisi",
				"man-of-the-match" : "Albert Bowman",
				"status" : "condimentum curabitur in libero ut"
			}, {
				"id" : 7,
				"match_date" : "10/11/2016",
				"host-team" : "Buzzshare",
				"guest-team" : "mattis pulvinar nulla",
				"ground" : "tincidunt eu felis",
				"man-of-the-match" : "Steven Boyd",
				"status" : "eros vestibulum ac est lacinia nisi venenatis"
			}, {
				"id" : 8,
				"match_date" : "1/11/2017",
				"host-team" : "Oyoloo",
				"guest-team" : "ullamcorper augue a suscipit",
				"ground" : "quisque porta volutpat",
				"man-of-the-match" : "Carlos Ferguson",
				"status" : "erat id mauris vulputate elementum nullam varius"
			}, {
				"id" : 9,
				"match_date" : "1/13/2017",
				"host-team" : "Quimm",
				"guest-team" : "curae duis faucibus",
				"ground" : "sapien placerat ante",
				"man-of-the-match" : "Gary Bishop",
				"status" : "ut nulla sed accumsan felis"
			}, {
				"id" : 10,
				"match_date" : "11/13/2016",
				"host-team" : null,
				"guest-team" : "dapibus at diam nam",
				"ground" : "non mattis pulvinar",
				"man-of-the-match" : null,
				"status" : "condimentum id luctus nec molestie sed"
			}, {
				"id" : 11,
				"match_date" : "2/9/2017",
				"host-team" : "Wordpedia",
				"guest-team" : "lorem ipsum dolor",
				"ground" : "quam fringilla rhoncus mauris",
				"man-of-the-match" : "Ernest Collins",
				"status" : "mattis odio donec vitae nisi"
			}, {
				"id" : 12,
				"match_date" : "10/19/2016",
				"host-team" : "Twimm",
				"guest-team" : "at velit vivamus",
				"ground" : "ornare consequat lectus",
				"man-of-the-match" : "Douglas Marshall",
				"status" : "scelerisque quam turpis adipiscing lorem vitae"
			}, {
				"id" : 13,
				"match_date" : "11/23/2016",
				"host-team" : "Wordtune",
				"guest-team" : "suspendisse potenti nullam",
				"ground" : "mi nulla ac",
				"man-of-the-match" : "Matthew Hansen",
				"status" : "nullam porttitor lacus at turpis donec posuere"
			}, {
				"id" : 14,
				"match_date" : "11/21/2016",
				"host-team" : "Katz",
				"guest-team" : "praesent lectus vestibulum quam",
				"ground" : "lorem ipsum dolor sit",
				"man-of-the-match" : "Scott Washington",
				"status" : "vel est donec odio justo"
			}, {
				"id" : 15,
				"match_date" : "10/31/2016",
				"host-team" : "Wikibox",
				"guest-team" : "sollicitudin ut suscipit a",
				"ground" : "est phasellus sit",
				"man-of-the-match" : "John Fisher",
				"status" : "velit id pretium iaculis diam erat"
			}, {
				"id" : 16,
				"match_date" : "12/29/2016",
				"host-team" : "Meemm",
				"guest-team" : "tristique in tempus",
				"ground" : "nisl venenatis lacinia aenean",
				"man-of-the-match" : "Michael Butler",
				"status" : "turpis nec euismod scelerisque quam"
			}, {
				"id" : 17,
				"match_date" : "12/2/2016",
				"host-team" : "Reallinks",
				"guest-team" : "nulla suscipit ligula in",
				"ground" : "dui proin leo",
				"man-of-the-match" : "Tina Mcdonald",
				"status" : "semper porta volutpat quam pede"
			}, {
				"id" : 18,
				"match_date" : "11/25/2016",
				"host-team" : "Browsebug",
				"guest-team" : "lobortis ligula sit",
				"ground" : "accumsan felis ut at",
				"man-of-the-match" : "Gary George",
				"status" : "aenean sit amet justo morbi"
			}, {
				"id" : 19,
				"match_date" : "11/15/2016",
				"host-team" : "Bluezoom",
				"guest-team" : "ac est lacinia",
				"ground" : "quam turpis adipiscing",
				"man-of-the-match" : "Harry Perkins",
				"status" : "nisi venenatis tristique fusce congue diam id"
			}, {
				"id" : 20,
				"match_date" : "11/29/2016",
				"host-team" : "Youspan",
				"guest-team" : "tortor sollicitudin mi",
				"ground" : "sed lacus morbi",
				"man-of-the-match" : "Jennifer Morgan",
				"status" : "in faucibus orci luctus et"
			}, {
				"id" : 21,
				"match_date" : "1/17/2017",
				"host-team" : "Meevee",
				"guest-team" : "penatibus et magnis",
				"ground" : "ut at dolor",
				"man-of-the-match" : "Stephanie Marshall",
				"status" : "in porttitor pede justo eu"
			}, {
				"id" : 22,
				"match_date" : "1/15/2017",
				"host-team" : "Trupe",
				"guest-team" : "cubilia curae duis faucibus",
				"ground" : "dictumst maecenas ut",
				"man-of-the-match" : "Kevin Morris",
				"status" : "turpis a pede posuere nonummy integer"
			}, {
				"id" : 23,
				"match_date" : "1/22/2017",
				"host-team" : "Meezzy",
				"guest-team" : "at feugiat non",
				"ground" : "neque vestibulum eget",
				"man-of-the-match" : "Willie Chapman",
				"status" : "amet nulla quisque arcu libero rutrum ac"
			}, {
				"id" : 24,
				"match_date" : "12/8/2016",
				"host-team" : null,
				"guest-team" : "ligula pellentesque ultrices phasellus",
				"ground" : "sit amet lobortis sapien",
				"man-of-the-match" : null,
				"status" : "sit amet diam in magna bibendum imperdiet"
			}, {
				"id" : 25,
				"match_date" : "11/14/2016",
				"host-team" : "Meemm",
				"guest-team" : "ac consequat metus sapien",
				"ground" : "massa id lobortis convallis",
				"man-of-the-match" : "Gerald Riley",
				"status" : "nunc purus phasellus in felis"
			}, {
				"id" : 26,
				"match_date" : "1/4/2017",
				"host-team" : "Thoughtbridge",
				"guest-team" : "congue eget semper rutrum",
				"ground" : "sit amet sem",
				"man-of-the-match" : "Cheryl Jones",
				"status" : "mi pede malesuada in imperdiet et commodo vulputate"
			}, {
				"id" : 27,
				"match_date" : "12/19/2016",
				"host-team" : "Centidel",
				"guest-team" : "montes nascetur ridiculus mus",
				"ground" : "erat tortor sollicitudin mi",
				"man-of-the-match" : "Pamela Sims",
				"status" : "eget nunc donec quis orci"
			}, {
				"id" : 28,
				"match_date" : "12/21/2016",
				"host-team" : "Demivee",
				"guest-team" : "sit amet consectetuer adipiscing",
				"ground" : "suspendisse ornare consequat lectus",
				"man-of-the-match" : "Russell Richards",
				"status" : "donec posuere metus vitae ipsum"
			}, {
				"id" : 29,
				"match_date" : "12/22/2016",
				"host-team" : "Mita",
				"guest-team" : "massa tempor convallis nulla",
				"ground" : "luctus et ultrices",
				"man-of-the-match" : "Keith Weaver",
				"status" : "venenatis non sodales sed tincidunt eu"
			}, {
				"id" : 30,
				"match_date" : "12/28/2016",
				"host-team" : "Thoughtstorm",
				"guest-team" : "felis donec semper",
				"ground" : "varius nulla facilisi cras",
				"man-of-the-match" : "Frank Jacobs",
				"status" : "magnis dis parturient montes nascetur ridiculus mus vivamus"
			}, {
				"id" : 31,
				"match_date" : "12/21/2016",
				"host-team" : "Avavee",
				"guest-team" : "volutpat sapien arcu",
				"ground" : "integer ac neque duis",
				"man-of-the-match" : "Joe Washington",
				"status" : "dapibus duis at velit eu est congue elementum"
			}, {
				"id" : 32,
				"match_date" : "11/16/2016",
				"host-team" : "Jaxbean",
				"guest-team" : "sit amet nulla",
				"ground" : "felis sed interdum",
				"man-of-the-match" : "Gloria Clark",
				"status" : "turpis sed ante vivamus tortor"
			}, {
				"id" : 33,
				"match_date" : "1/12/2017",
				"host-team" : "Flipopia",
				"guest-team" : "mi pede malesuada in",
				"ground" : "pede lobortis ligula",
				"man-of-the-match" : "Stephen Harrison",
				"status" : "vestibulum ante ipsum primis in faucibus orci"
			}, {
				"id" : 34,
				"match_date" : "1/3/2017",
				"host-team" : null,
				"guest-team" : "odio donec vitae",
				"ground" : "turpis eget elit sodales",
				"man-of-the-match" : null,
				"status" : "potenti in eleifend quam a"
			}, {
				"id" : 35,
				"match_date" : "2/1/2017",
				"host-team" : "Devpulse",
				"guest-team" : "amet consectetuer adipiscing elit",
				"ground" : "nullam varius nulla facilisi",
				"man-of-the-match" : "Walter Gonzalez",
				"status" : "augue a suscipit nulla elit ac"
			}, {
				"id" : 36,
				"match_date" : "2/3/2017",
				"host-team" : "Gabtune",
				"guest-team" : "pretium nisl ut",
				"ground" : "sit amet justo",
				"man-of-the-match" : "Carol Morrison",
				"status" : "in ante vestibulum ante ipsum primis in faucibus"
			}, {
				"id" : 37,
				"match_date" : "1/17/2017",
				"host-team" : "Camido",
				"guest-team" : "quis augue luctus",
				"ground" : "sit amet nulla quisque",
				"man-of-the-match" : "Jose Welch",
				"status" : "pretium iaculis justo in hac habitasse platea dictumst"
			}, {
				"id" : 38,
				"match_date" : "2/3/2017",
				"host-team" : null,
				"guest-team" : "dui nec nisi volutpat",
				"ground" : "in faucibus orci",
				"man-of-the-match" : null,
				"status" : "lobortis vel dapibus at diam nam"
			}, {
				"id" : 39,
				"match_date" : "1/27/2017",
				"host-team" : "Skilith",
				"guest-team" : "ultrices posuere cubilia curae",
				"ground" : "ultrices vel augue vestibulum",
				"man-of-the-match" : "Debra Rose",
				"status" : "ut nunc vestibulum ante ipsum primis"
			}, {
				"id" : 40,
				"match_date" : "11/18/2016",
				"host-team" : "Gabvine",
				"guest-team" : "maecenas rhoncus aliquam lacus",
				"ground" : "blandit lacinia erat vestibulum",
				"man-of-the-match" : "Jack Wright",
				"status" : "ante nulla justo aliquam quis"
			}, {
				"id" : 41,
				"match_date" : "10/30/2016",
				"host-team" : "Agivu",
				"guest-team" : "vel ipsum praesent blandit",
				"ground" : "amet nunc viverra dapibus",
				"man-of-the-match" : "Dorothy Nelson",
				"status" : "pede justo lacinia eget tincidunt eget tempus"
			}, {
				"id" : 42,
				"match_date" : "1/27/2017",
				"host-team" : "Shufflester",
				"guest-team" : "purus eu magna vulputate",
				"ground" : "proin eu mi",
				"man-of-the-match" : "Howard Hughes",
				"status" : "sit amet erat nulla tempus vivamus"
			}, {
				"id" : 43,
				"match_date" : "11/30/2016",
				"host-team" : "Gabspot",
				"guest-team" : "bibendum morbi non quam",
				"ground" : "justo sollicitudin ut",
				"man-of-the-match" : "Lori Boyd",
				"status" : "lacus at velit vivamus vel nulla eget"
			}, {
				"id" : 44,
				"match_date" : "10/23/2016",
				"host-team" : "Yotz",
				"guest-team" : "sapien placerat ante",
				"ground" : "turpis donec posuere",
				"man-of-the-match" : "Gloria Taylor",
				"status" : "nam ultrices libero non mattis pulvinar nulla"
			}, {
				"id" : 45,
				"match_date" : "12/15/2016",
				"host-team" : "Zoomcast",
				"guest-team" : "congue elementum in hac",
				"ground" : "ornare imperdiet sapien",
				"man-of-the-match" : "Joan Hamilton",
				"status" : "sem praesent id massa id nisl"
			}, {
				"id" : 46,
				"match_date" : "1/20/2017",
				"host-team" : "Tazz",
				"guest-team" : "massa id lobortis",
				"ground" : "elit ac nulla sed",
				"man-of-the-match" : "Lois Sanders",
				"status" : "eu est congue elementum in hac habitasse platea"
			}, {
				"id" : 47,
				"match_date" : "11/21/2016",
				"host-team" : "Twitterbridge",
				"guest-team" : "nunc proin at",
				"ground" : "eget elit sodales",
				"man-of-the-match" : "Brandon Reid",
				"status" : "ut mauris eget massa tempor convallis nulla neque"
			}, {
				"id" : 48,
				"match_date" : "10/8/2016",
				"host-team" : "Wikibox",
				"guest-team" : "sollicitudin vitae consectetuer",
				"ground" : "enim sit amet nunc",
				"man-of-the-match" : "Roy Edwards",
				"status" : "in porttitor pede justo eu massa donec dapibus"
			}, {
				"id" : 49,
				"match_date" : "1/27/2017",
				"host-team" : "Camido",
				"guest-team" : "accumsan tellus nisi eu",
				"ground" : "justo sit amet sapien",
				"man-of-the-match" : "Ryan Gonzales",
				"status" : "potenti cras in purus eu magna vulputate luctus"
			}, {
				"id" : 50,
				"match_date" : "2/6/2017",
				"host-team" : "InnoZ",
				"guest-team" : "nibh quisque id",
				"ground" : "at nulla suspendisse",
				"man-of-the-match" : "Ryan Matthews",
				"status" : "arcu adipiscing molestie hendrerit at"
			}, {
				"id" : 51,
				"match_date" : "10/23/2016",
				"host-team" : "Skyndu",
				"guest-team" : "non velit donec",
				"ground" : "fringilla rhoncus mauris",
				"man-of-the-match" : "Kathy Dean",
				"status" : "magna vulputate luctus cum sociis natoque penatibus"
			}, {
				"id" : 52,
				"match_date" : "10/17/2016",
				"host-team" : "Eazzy",
				"guest-team" : "ante vestibulum ante",
				"ground" : "quis justo maecenas rhoncus",
				"man-of-the-match" : "Lori Shaw",
				"status" : "purus eu magna vulputate luctus cum"
			}, {
				"id" : 53,
				"match_date" : "12/26/2016",
				"host-team" : "Oyope",
				"guest-team" : "felis donec semper",
				"ground" : "non lectus aliquam",
				"man-of-the-match" : "Gregory Lee",
				"status" : "diam cras pellentesque volutpat dui maecenas tristique"
			}, {
				"id" : 54,
				"match_date" : "1/21/2017",
				"host-team" : "Voolith",
				"guest-team" : "sit amet eleifend pede",
				"ground" : "at nunc commodo placerat",
				"man-of-the-match" : "Susan Burton",
				"status" : "ut odio cras mi pede"
			}, {
				"id" : 55,
				"match_date" : "12/6/2016",
				"host-team" : "Thoughtbridge",
				"guest-team" : "ac tellus semper interdum",
				"ground" : "molestie lorem quisque ut",
				"man-of-the-match" : "Nicholas Woods",
				"status" : "in purus eu magna vulputate luctus cum"
			}, {
				"id" : 56,
				"match_date" : "10/27/2016",
				"host-team" : "Devpulse",
				"guest-team" : "nunc vestibulum ante",
				"ground" : "felis sed interdum",
				"man-of-the-match" : "Louis Hall",
				"status" : "in congue etiam justo etiam pretium iaculis justo"
			}, {
				"id" : 57,
				"match_date" : "1/25/2017",
				"host-team" : "Yamia",
				"guest-team" : "metus arcu adipiscing molestie",
				"ground" : "rutrum ac lobortis vel",
				"man-of-the-match" : "Susan Bryant",
				"status" : "tempor convallis nulla neque libero convallis eget"
			}, {
				"id" : 58,
				"match_date" : "12/26/2016",
				"host-team" : "Brightbean",
				"guest-team" : "nulla neque libero",
				"ground" : "primis in faucibus",
				"man-of-the-match" : "Mary Rose",
				"status" : "ornare consequat lectus in est risus"
			}, {
				"id" : 59,
				"match_date" : "10/4/2016",
				"host-team" : "Midel",
				"guest-team" : "vel accumsan tellus nisi",
				"ground" : "leo pellentesque ultrices",
				"man-of-the-match" : "Thomas Hunt",
				"status" : "eget rutrum at lorem integer"
			}, {
				"id" : 60,
				"match_date" : "12/3/2016",
				"host-team" : "Bluezoom",
				"guest-team" : "venenatis non sodales sed",
				"ground" : "in hac habitasse platea",
				"man-of-the-match" : "Gary Carter",
				"status" : "sapien in sapien iaculis congue vivamus"
			}, {
				"id" : 61,
				"match_date" : "11/9/2016",
				"host-team" : "Skaboo",
				"guest-team" : "a suscipit nulla elit",
				"ground" : "ut suscipit a feugiat",
				"man-of-the-match" : "Ralph Evans",
				"status" : "urna ut tellus nulla ut"
			}, {
				"id" : 62,
				"match_date" : "11/27/2016",
				"host-team" : "Fatz",
				"guest-team" : "in eleifend quam a",
				"ground" : "iaculis justo in",
				"man-of-the-match" : "Eric Payne",
				"status" : "suscipit a feugiat et eros"
			}, {
				"id" : 63,
				"match_date" : "2/4/2017",
				"host-team" : "Devpulse",
				"guest-team" : "odio donec vitae nisi",
				"ground" : "et commodo vulputate",
				"man-of-the-match" : "Margaret Powell",
				"status" : "in hac habitasse platea dictumst maecenas"
			}, {
				"id" : 64,
				"match_date" : "1/10/2017",
				"host-team" : "Riffwire",
				"guest-team" : "pede justo lacinia eget",
				"ground" : "praesent blandit nam nulla",
				"man-of-the-match" : "Donna Garza",
				"status" : "mauris vulputate elementum nullam varius"
			}, {
				"id" : 65,
				"match_date" : "10/17/2016",
				"host-team" : "Youspan",
				"guest-team" : "aliquet pulvinar sed nisl",
				"ground" : "molestie sed justo pellentesque",
				"man-of-the-match" : "Lisa Gonzalez",
				"status" : "magna bibendum imperdiet nullam orci pede venenatis non"
			}, {
				"id" : 66,
				"match_date" : "1/8/2017",
				"host-team" : "Mynte",
				"guest-team" : "parturient montes nascetur ridiculus",
				"ground" : "nec dui luctus rutrum",
				"man-of-the-match" : "Thomas Stone",
				"status" : "sapien dignissim vestibulum vestibulum ante"
			}, {
				"id" : 67,
				"match_date" : "10/7/2016",
				"host-team" : "Kimia",
				"guest-team" : "nunc rhoncus dui",
				"ground" : "nisi volutpat eleifend",
				"man-of-the-match" : "Gerald Black",
				"status" : "consequat in consequat ut nulla sed accumsan felis"
			}, {
				"id" : 68,
				"match_date" : "12/20/2016",
				"host-team" : "Brightdog",
				"guest-team" : "luctus cum sociis natoque",
				"ground" : "nulla mollis molestie lorem",
				"man-of-the-match" : "Catherine Fox",
				"status" : "eget rutrum at lorem integer tincidunt ante vel"
			}, {
				"id" : 69,
				"match_date" : "12/1/2016",
				"host-team" : "Eidel",
				"guest-team" : "vitae consectetuer eget",
				"ground" : "vivamus tortor duis",
				"man-of-the-match" : "Randy Spencer",
				"status" : "et ultrices posuere cubilia curae"
			}, {
				"id" : 70,
				"match_date" : "1/20/2017",
				"host-team" : "Rhycero",
				"guest-team" : "diam id ornare",
				"ground" : "quam sollicitudin vitae consectetuer",
				"man-of-the-match" : "Fred Peterson",
				"status" : "in tempus sit amet sem fusce consequat"
			}, {
				"id" : 71,
				"match_date" : "1/12/2017",
				"host-team" : "Dabvine",
				"guest-team" : "purus aliquet at feugiat",
				"ground" : "et magnis dis",
				"man-of-the-match" : "Gregory Simmons",
				"status" : "rhoncus dui vel sem sed sagittis"
			}, {
				"id" : 72,
				"match_date" : "1/8/2017",
				"host-team" : "Twinder",
				"guest-team" : "aliquet maecenas leo odio",
				"ground" : "maecenas pulvinar lobortis",
				"man-of-the-match" : "Benjamin Morrison",
				"status" : "diam nam tristique tortor eu pede"
			}, {
				"id" : 73,
				"match_date" : "12/20/2016",
				"host-team" : "Topicware",
				"guest-team" : "maecenas leo odio condimentum",
				"ground" : "ut blandit non",
				"man-of-the-match" : "Irene Burns",
				"status" : "enim sit amet nunc viverra"
			}, {
				"id" : 74,
				"match_date" : "11/10/2016",
				"host-team" : "Tagcat",
				"guest-team" : "massa quis augue",
				"ground" : "adipiscing lorem vitae",
				"man-of-the-match" : "Jessica West",
				"status" : "maecenas pulvinar lobortis est phasellus sit amet"
			}, {
				"id" : 75,
				"match_date" : "11/12/2016",
				"host-team" : "Latz",
				"guest-team" : "luctus et ultrices posuere",
				"ground" : "at dolor quis odio",
				"man-of-the-match" : "Amy Gilbert",
				"status" : "amet lobortis sapien sapien non mi"
			}, {
				"id" : 76,
				"match_date" : "1/7/2017",
				"host-team" : "Gevee",
				"guest-team" : "magna at nunc commodo",
				"ground" : "vehicula condimentum curabitur",
				"man-of-the-match" : "Mildred Alvarez",
				"status" : "nisi venenatis tristique fusce congue"
			}, {
				"id" : 77,
				"match_date" : "12/13/2016",
				"host-team" : "Kimia",
				"guest-team" : "in faucibus orci luctus",
				"ground" : "nulla integer pede",
				"man-of-the-match" : "Charles Chapman",
				"status" : "in tempor turpis nec euismod scelerisque"
			}, {
				"id" : 78,
				"match_date" : "10/8/2016",
				"host-team" : "Skiba",
				"guest-team" : "consectetuer adipiscing elit",
				"ground" : "libero nam dui",
				"man-of-the-match" : "Alice Matthews",
				"status" : "lacus at turpis donec posuere metus"
			}, {
				"id" : 79,
				"match_date" : "10/31/2016",
				"host-team" : "Vidoo",
				"guest-team" : "eu orci mauris lacinia",
				"ground" : "nibh fusce lacus purus",
				"man-of-the-match" : "Mildred Owens",
				"status" : "lobortis vel dapibus at diam"
			}, {
				"id" : 80,
				"match_date" : "2/6/2017",
				"host-team" : "Dazzlesphere",
				"guest-team" : "vel ipsum praesent",
				"ground" : "fringilla rhoncus mauris enim",
				"man-of-the-match" : "Rose Williams",
				"status" : "turpis eget elit sodales scelerisque mauris sit amet"
			}, {
				"id" : 81,
				"match_date" : "12/26/2016",
				"host-team" : "Layo",
				"guest-team" : "at vulputate vitae",
				"ground" : "justo aliquam quis turpis",
				"man-of-the-match" : "Patrick Dunn",
				"status" : "duis mattis egestas metus aenean fermentum donec"
			}, {
				"id" : 82,
				"match_date" : "11/19/2016",
				"host-team" : "Feedfish",
				"guest-team" : "duis ac nibh fusce",
				"ground" : "vel sem sed sagittis",
				"man-of-the-match" : "Terry Garza",
				"status" : "nunc nisl duis bibendum felis sed"
			}, {
				"id" : 83,
				"match_date" : "11/9/2016",
				"host-team" : "Agivu",
				"guest-team" : "in sagittis dui",
				"ground" : "at nunc commodo",
				"man-of-the-match" : "Brian Jones",
				"status" : "in hac habitasse platea dictumst morbi"
			}, {
				"id" : 84,
				"match_date" : "11/1/2016",
				"host-team" : "Browseblab",
				"guest-team" : "iaculis justo in",
				"ground" : "mattis pulvinar nulla pede",
				"man-of-the-match" : "Evelyn Wood",
				"status" : "proin risus praesent lectus vestibulum quam sapien varius"
			}, {
				"id" : 85,
				"match_date" : "12/22/2016",
				"host-team" : "Lazz",
				"guest-team" : "cubilia curae mauris viverra",
				"ground" : "nunc viverra dapibus nulla",
				"man-of-the-match" : "Kathleen Sims",
				"status" : "lacinia sapien quis libero nullam sit amet turpis"
			}, {
				"id" : 86,
				"match_date" : "11/3/2016",
				"host-team" : "Jabbercube",
				"guest-team" : "est congue elementum in",
				"ground" : "non pretium quis lectus",
				"man-of-the-match" : "Kimberly Morgan",
				"status" : "aliquet pulvinar sed nisl nunc rhoncus dui vel"
			}, {
				"id" : 87,
				"match_date" : "1/20/2017",
				"host-team" : "Youfeed",
				"guest-team" : "amet consectetuer adipiscing",
				"ground" : "lectus vestibulum quam",
				"man-of-the-match" : "Matthew Wells",
				"status" : "pede lobortis ligula sit amet eleifend"
			}, {
				"id" : 88,
				"match_date" : "10/6/2016",
				"host-team" : "Blogspan",
				"guest-team" : "lacinia erat vestibulum",
				"ground" : "elementum nullam varius",
				"man-of-the-match" : "Thomas Watkins",
				"status" : "nulla eget eros elementum pellentesque quisque porta volutpat"
			}, {
				"id" : 89,
				"match_date" : "1/29/2017",
				"host-team" : "Zoomzone",
				"guest-team" : "luctus et ultrices posuere",
				"ground" : "nullam varius nulla facilisi",
				"man-of-the-match" : "Janet Hudson",
				"status" : "phasellus in felis donec semper sapien a"
			}, {
				"id" : 90,
				"match_date" : "2/3/2017",
				"host-team" : "Pixonyx",
				"guest-team" : "augue a suscipit nulla",
				"ground" : "nulla tempus vivamus in",
				"man-of-the-match" : "Jean Jackson",
				"status" : "justo lacinia eget tincidunt eget tempus"
			}, {
				"id" : 91,
				"match_date" : "11/26/2016",
				"host-team" : "Gabtype",
				"guest-team" : "ut odio cras mi",
				"ground" : "molestie sed justo pellentesque",
				"man-of-the-match" : "Louis Owens",
				"status" : "vel lectus in quam fringilla rhoncus mauris enim"
			}, {
				"id" : 92,
				"match_date" : "2/2/2017",
				"host-team" : "Nlounge",
				"guest-team" : "quis turpis eget",
				"ground" : "mus vivamus vestibulum",
				"man-of-the-match" : "Henry Reynolds",
				"status" : "integer a nibh in quis justo maecenas"
			}, {
				"id" : 93,
				"match_date" : "11/21/2016",
				"host-team" : "Oozz",
				"guest-team" : "semper sapien a",
				"ground" : "aliquam non mauris morbi",
				"man-of-the-match" : "Terry Wallace",
				"status" : "luctus ultricies eu nibh quisque id"
			}, {
				"id" : 94,
				"match_date" : "11/18/2016",
				"host-team" : "Edgeclub",
				"guest-team" : "maecenas tincidunt lacus at",
				"ground" : "semper interdum mauris",
				"man-of-the-match" : "Jonathan Jacobs",
				"status" : "quis justo maecenas rhoncus aliquam"
			}, {
				"id" : 95,
				"match_date" : "10/13/2016",
				"host-team" : "Wordify",
				"guest-team" : "volutpat eleifend donec ut",
				"ground" : "justo sit amet",
				"man-of-the-match" : "Harry Rivera",
				"status" : "justo etiam pretium iaculis justo in hac"
			}, {
				"id" : 96,
				"match_date" : "11/27/2016",
				"host-team" : "Meevee",
				"guest-team" : "tellus nisi eu",
				"ground" : "morbi vestibulum velit id",
				"man-of-the-match" : "Nancy Miller",
				"status" : "primis in faucibus orci luctus et ultrices"
			}, {
				"id" : 97,
				"match_date" : "11/18/2016",
				"host-team" : null,
				"guest-team" : "id massa id nisl",
				"ground" : "tellus nisi eu orci",
				"man-of-the-match" : null,
				"status" : "metus aenean fermentum donec ut mauris eget massa"
			}, {
				"id" : 98,
				"match_date" : "10/4/2016",
				"host-team" : "Trudeo",
				"guest-team" : "ante ipsum primis",
				"ground" : "ante ipsum primis",
				"man-of-the-match" : "Norma Carpenter",
				"status" : "praesent id massa id nisl venenatis lacinia"
			}, {
				"id" : 99,
				"match_date" : "11/16/2016",
				"host-team" : "Quatz",
				"guest-team" : "volutpat convallis morbi",
				"ground" : "erat quisque erat",
				"man-of-the-match" : "Jason Garrett",
				"status" : "purus aliquet at feugiat non pretium quis lectus"
			}, {
				"id" : 100,
				"match_date" : "11/17/2016",
				"host-team" : null,
				"guest-team" : "fusce posuere felis sed",
				"ground" : "vel enim sit",
				"man-of-the-match" : null,
				"status" : "accumsan tortor quis turpis sed"
			} ];
			return data;
		};
	}
})();