/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
    config.extraPlugins = 'youtube';
    config.youtube_related = true;
    config.youtube_older = false;
    config.youtube_privacy = false;
    config.removePlugins = 'iframe';
    // Define changes to default configuration here. For example:
	// config.language = 'fr';
	config.uiColor = '#AADC6E';
	// config.height = 300;
	// config.toolbarCanCollapse = true;
};
