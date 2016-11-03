package org.bionic.controller;

import java.util.List;

import org.bionic.entity.Rang;
import org.bionic.service.RangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class RangController {
	@Autowired
	private RangService rangService;
	
	    // create rang
		@RequestMapping(value = "/rang/", method = RequestMethod.POST)
		public ResponseEntity<Void> createRang(@RequestBody Rang rang, UriComponentsBuilder ucBuilder) {
			rangService.save(rang);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/rang/{rangId}").buildAndExpand(rang.getRangId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		// edit rang
		@RequestMapping(value = "/rang/{rangId}", method = RequestMethod.PUT)
		public ResponseEntity<Rang> updateRang(@PathVariable("rangId") long rangId, @RequestBody Rang rang) {
			Rang currentRang = rangService.findOne(rangId);
			if (currentRang == null) {
				return new ResponseEntity<Rang>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Rang>(rangService.save(rang), HttpStatus.OK);
		}

		// delete rang
		@RequestMapping(value = "/rang/{rangId}", method = RequestMethod.DELETE)
	    public ResponseEntity<Rang> deleteRang(@PathVariable("rangId") long rangId) {
	        Rang rang = rangService.findOne(rangId);
	        if (rang == null) {
	            return new ResponseEntity<Rang>(HttpStatus.NOT_FOUND);
	        }

	        rangService.delete(rangService.findOne(rangId));
	        return new ResponseEntity<Rang>(HttpStatus.NO_CONTENT);
	    }

		// show rang by id
		@RequestMapping(value = "/rang/{rangId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Rang> getRang(@PathVariable("rangId") long rangId) {
			Rang rang = rangService.findOne(rangId);
			if (rang == null) {
				return new ResponseEntity<Rang>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Rang>(rang, HttpStatus.OK);
		}

		// show all rangs
		@RequestMapping(value = "/rang/", method = RequestMethod.GET)
		public ResponseEntity<List<Rang>> listAllRangs() {
			List<Rang> rang = rangService.findAll();
			if (rang.isEmpty()) {
				return new ResponseEntity<List<Rang>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Rang>>(rang, HttpStatus.OK);
		}
}
