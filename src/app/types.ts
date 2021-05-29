export enum MediaType {
	Unknown = 0,
	YouTube = 1,
	Spotify = 2,
}

export interface PlaybackMedia {
	id: string;
	media_type: MediaType;
	thumbnail_url: string;
	media_title: string;
	media_author: string;
	media_total_time: number;
}
